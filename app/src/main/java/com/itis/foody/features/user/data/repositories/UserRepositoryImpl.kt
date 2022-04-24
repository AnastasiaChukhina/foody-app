package com.itis.foody.features.user.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.dao.UserDao
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.user.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(id: Int): User = userDao.getUserById(id)

    override suspend fun logout(): FirebaseUser? {
        auth.signOut()
        return auth.currentUser
    }
}
