package com.itis.foody.features.signin.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.dao.UserDao
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signin.domain.exceptions.FirebaseAuthFailedException
import com.itis.foody.features.signin.domain.exceptions.UnknownEmailException
import com.itis.foody.features.signin.domain.models.UserForm
import com.itis.foody.features.signin.domain.repositories.SignInRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignInRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userDao: UserDao
) : SignInRepository {

    override suspend fun auth(user: UserForm): User? {
        val firebaseUser = getFirebaseUser(user.email, user.password)
        return getUserByEmail(firebaseUser.email)
    }

    private suspend fun getFirebaseUser(email: String, password: String): FirebaseUser =
        suspendCoroutine {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser = auth.currentUser
                            if (firebaseUser != null) {
                                it.resume(firebaseUser)
                            } else it.resumeWithException(
                                FirebaseAuthFailedException("Auth Failed")
                            )
                        }
                    }
            } catch (e: Exception) {
                it.resumeWithException(UnknownEmailException("Unknown email"))
            }
        }

    private suspend fun getUserByEmail(email: String?): User? = email?.let {
        userDao.getUserByEmail(
            it
        )
    }
}
