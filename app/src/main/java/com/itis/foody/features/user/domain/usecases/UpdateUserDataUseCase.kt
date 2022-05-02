package com.itis.foody.features.user.domain.usecases

import com.itis.foody.common.db.entities.User
import com.itis.foody.features.user.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateUserDataUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(username: String, email: String, id: Int): User {
        return withContext(dispatcher) {
            userRepository.changeUserData(username, email, id)
        }
    }
}
