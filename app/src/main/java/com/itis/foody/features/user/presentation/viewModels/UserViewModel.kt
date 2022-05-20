package com.itis.foody.features.user.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.features.user.domain.models.Account
import com.itis.foody.features.user.domain.usecases.GetUserUseCase
import com.itis.foody.features.user.domain.usecases.LogoutUseCase
import com.itis.foody.features.user.domain.usecases.UpdateUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var _user: MutableLiveData<Result<FirebaseUser?>> = MutableLiveData()
    val user: LiveData<Result<FirebaseUser?>> = _user

    private var _sessionUser: MutableLiveData<Result<Account>> = MutableLiveData()
    val sessionUser: LiveData<Result<Account>> = _sessionUser

    private var _updatedUser: MutableLiveData<Result<Account>> = MutableLiveData()
    val updatedUser: LiveData<Result<Account>> = _updatedUser

    fun getSessionUser() {
        viewModelScope.launch {
            kotlin.runCatching {
                getUserUseCase()
            }.onSuccess {
                _sessionUser.value = Result.success(it)
            }.onFailure {
                _sessionUser.value = Result.failure(it)
            }
        }
    }

    fun changeUserData(username: String, email: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                updateUserDataUseCase(username, email, password)
            }.onSuccess {
                _updatedUser.value = Result.success(it)
            }.onFailure {
                _updatedUser.value = Result.failure(it)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            kotlin.runCatching {
                logoutUseCase()
            }.onSuccess {
                _user.value = Result.success(it)
            }.onFailure {
                _user.value = Result.failure(it)
            }
        }
    }
}
