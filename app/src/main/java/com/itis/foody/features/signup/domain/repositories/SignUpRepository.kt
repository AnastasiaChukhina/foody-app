package com.itis.foody.features.signup.domain.repositories

import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signup.domain.models.UserForm

interface SignUpRepository {
    suspend fun registerUser(user: UserForm): User
}
