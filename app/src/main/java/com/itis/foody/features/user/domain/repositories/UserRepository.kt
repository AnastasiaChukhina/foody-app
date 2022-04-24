package com.itis.foody.features.user.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.entities.User

interface UserRepository {
    suspend fun getUser(id: Int): User
    suspend fun logout(): FirebaseUser?
}
