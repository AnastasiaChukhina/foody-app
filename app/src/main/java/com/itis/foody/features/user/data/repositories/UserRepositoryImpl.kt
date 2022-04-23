package com.itis.foody.features.user.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.features.user.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): UserRepository {

    override suspend fun logout(): FirebaseUser? {
        auth.signOut()
        return auth.currentUser
    }
}
