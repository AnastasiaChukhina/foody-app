package com.itis.foody.features.user.domain.repositories

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun logout(): FirebaseUser?
}
