package com.itis.foody.features.user.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.user.domain.models.Account

interface UserRepository {
    suspend fun getUser(id: Int): Account
    suspend fun changeUserData(username: String, email: String, id: Int): Account
    suspend fun logout(): FirebaseUser?
}
