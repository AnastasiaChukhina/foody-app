package com.itis.foody.features.signup.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.utils.SingleLiveEvent
import com.itis.foody.features.signup.domain.models.UserForm
import com.itis.foody.features.signup.domain.usecases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private var _user: SingleLiveEvent<Result<FirebaseUser>> = SingleLiveEvent()
    val user: SingleLiveEvent<Result<FirebaseUser>> = _user

    fun registerUser(user: UserForm) {
        viewModelScope.launch {
            try {
                val firebaseUser = registerUserUseCase(user)
                _user.value = Result.success(firebaseUser)
            } catch (e: Exception) {
                _user.value = Result.failure(e)
            }
        }
    }
}
