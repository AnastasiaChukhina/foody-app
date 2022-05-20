package com.itis.foody.features.user.data.repositories

import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.dao.UserDao
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.recipe.domain.mappers.ModelMapper
import com.itis.foody.features.user.domain.models.Account
import com.itis.foody.features.user.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userDao: UserDao,
    private val mapper: ModelMapper<User, Account>
) : UserRepository {

    override suspend fun getUser(id: Int): Account = mapper.map(getDbUser(id))

    override suspend fun changeUserData(username: String, email: String, id: Int): Account {
        val user = getDbUser(id)
        return mapper.map(processUserData(username, email, user))
    }

    override suspend fun logout(): FirebaseUser? {
        auth.signOut()
        return auth.currentUser
    }

    private suspend fun changeUsername(username: String, user: User) {
        user.username = username
        userDao.updateUser(user)
    }

    private fun verifyAndUpdate(password: String, user: User) {
            val credential = EmailAuthProvider.getCredential(user.email, user.password)
            auth.currentUser?.apply {
                reauthenticate(credential)
                    .addOnCompleteListener {
                        updateEmailInCloud(password)
                    }
            }
        }

    private fun updateEmailInCloud(email: String) {
        auth.currentUser?.apply {
            updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("EMAIL_CHANGE", "Successfully changed")
                    } else {
                        Log.e("EMAIL_CHANGE", "Unsuccessful try")
                    }
                }
        }
    }

    private suspend fun processUserData(username: String, email: String, user: User): User {
        if (username != user.username) {
            changeUsername(username, user)
            user.username = username
        }
        if (email != user.email) {
            verifyAndUpdate(email, user)
            updateEmailInDatabase(email, user)
            user.email = email
        }
        return user
    }

    private suspend fun updateEmailInDatabase(email: String, user: User) {
        user.email = email
        userDao.updateUser(user)
    }

    private suspend fun getDbUser(id: Int): User = userDao.getUserById(id)
}
