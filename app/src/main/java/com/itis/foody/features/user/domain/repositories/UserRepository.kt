package com.itis.foody.features.user.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.itis.foody.features.user.domain.models.Account

interface UserRepository {
    suspend fun getUser(): Account
    suspend fun changeUserData(username: String, email: String, password: String): Account
    suspend fun logout(): FirebaseUser?
}
