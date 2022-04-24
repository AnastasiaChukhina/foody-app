package com.itis.foody.features.signin.domain.usecases

import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signin.domain.models.UserForm
import com.itis.foody.features.signin.domain.repositories.SignInRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(user: UserForm): User? {
        return withContext(dispatcher) {
            signInRepository.auth(user)
        }
    }
}
