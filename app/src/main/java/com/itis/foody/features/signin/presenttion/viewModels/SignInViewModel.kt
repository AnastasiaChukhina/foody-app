package com.itis.foody.features.signin.presenttion.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.utils.SingleLiveEvent
import com.itis.foody.features.signin.domain.models.UserForm
import com.itis.foody.features.signin.domain.usecases.AuthUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase
): ViewModel() {

    private var _user: SingleLiveEvent<Result<FirebaseUser>> = SingleLiveEvent()
    val user: SingleLiveEvent<Result<FirebaseUser>> = _user

    fun authUser(user: UserForm) {
        viewModelScope.launch {
            try {
                val firebaseUser = authUserUseCase(user)
                _user.value = Result.success(firebaseUser)
            } catch (e: Exception) {
                _user.value = Result.failure(e)
            }
        }
    }
}
