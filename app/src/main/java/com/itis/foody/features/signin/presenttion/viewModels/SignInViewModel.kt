package com.itis.foody.features.signin.presenttion.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signin.domain.models.UserForm
import com.itis.foody.features.signin.domain.usecases.AuthUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    private val authUserUseCase: AuthUserUseCase
) : ViewModel() {

    private var _user: MutableLiveData<Result<User?>> = MutableLiveData()
    val user: LiveData<Result<User?>> = _user

    fun authUser(user: UserForm) {
        viewModelScope.launch {
            try {
                val firebaseUser = authUserUseCase(user)
                _user.value = Result.success(firebaseUser)
                firebaseUser?.id?.let {
                    saveSession(it)
                }
            } catch (e: Exception) {
                _user.value = Result.failure(e)
            }
        }
    }

    private fun saveSession(id: Int) {
        preferences.edit()
            .putInt("userId", id)
            .apply()
    }
}
