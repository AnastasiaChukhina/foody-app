package com.itis.foody.features.user.presentation.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.user.domain.exceptions.SessionNotAvailableException
import com.itis.foody.features.user.domain.usecases.GetUserUseCase
import com.itis.foody.features.user.domain.usecases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var _user: MutableLiveData<Result<FirebaseUser?>> = MutableLiveData()
    val user: LiveData<Result<FirebaseUser?>> = _user

    private var _sessionUser: MutableLiveData<Result<User>> = MutableLiveData()
    val sessionUser: LiveData<Result<User>> = _sessionUser

    fun getSessionUser() {
        viewModelScope.launch {
            try {
                val id = getSessionId()
                val user = getUserUseCase(id)
                _sessionUser.value = Result.success(user)
            } catch (e: Exception) {
                _sessionUser.value = Result.failure(e)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                val firebaseUser = logoutUseCase()
                _user.value = Result.success(firebaseUser)
                removeSession()
            } catch (e: Exception) {
                _user.value = Result.failure(e)
            }
        }
    }

    private fun getSessionId(): Int {
        val id = preferences.getInt("userId", -1)
        if (id != -1) return id
        else throw SessionNotAvailableException("SessionNotAvailable")
    }

    private fun removeSession() {
        preferences.edit()
            .remove("userId")
            .apply()
    }
}
