package com.itis.foody.features.signin.domain.repositories

import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signin.domain.models.UserForm

interface SignInRepository {
    suspend fun auth(user: UserForm): User?
}
