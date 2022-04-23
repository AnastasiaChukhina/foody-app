package com.itis.foody.features.signup.domain.usecases

import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signup.domain.models.UserForm
import com.itis.foody.features.signup.domain.repositories.SignUpRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(user: UserForm): User {
        return withContext(dispatcher) {
            signUpRepository.registerUser(user)
        }
    }
}
