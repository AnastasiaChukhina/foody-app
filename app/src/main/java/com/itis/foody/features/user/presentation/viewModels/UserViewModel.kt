package com.itis.foody.features.user.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.features.user.domain.usecases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private var _user: MutableLiveData<Result<FirebaseUser?>> = MutableLiveData()
    val user: LiveData<Result<FirebaseUser?>> = _user

    fun logout() {
        viewModelScope.launch {
            try {
                val firebaseUser = logoutUseCase()
                _user.value = Result.success(firebaseUser)
            } catch (e: Exception) {
                _user.value = Result.failure(e)
            }
        }
    }
}
