package com.itis.foody.features.signup.presentation.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signup.domain.models.UserForm
import com.itis.foody.features.signup.domain.usecases.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val preferences: SharedPreferences,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private var _user: MutableLiveData<Result<User>> = MutableLiveData()
    val user: LiveData<Result<User>> = _user

    fun registerUser(user: UserForm) {
        viewModelScope.launch {
            try {
                val firebaseUser = registerUserUseCase(user)
                _user.value = Result.success(firebaseUser)
                saveSession(firebaseUser.id)
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
