package com.itis.foody.features.signup.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.itis.foody.common.db.dao.UserDao
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signup.domain.exceptions.RegistrationFailedException
import com.itis.foody.features.signup.domain.exceptions.SuchEmailAlreadyRegisteredException
import com.itis.foody.features.signup.domain.models.UserForm
import com.itis.foody.features.signup.domain.repositories.SignUpRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userDao: UserDao
) : SignUpRepository {

    override suspend fun registerUser(user: UserForm): User {
        saveToCloud(user)
        return saveToDatabase(user)
    }

    private suspend fun saveToDatabase(user: UserForm): User {
        userDao.save(User(0, user.username, user.email, user.password))
        return userDao.getUserByEmail(user.email)
    }

    private suspend fun saveToCloud(user: UserForm): FirebaseUser = suspendCoroutine {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    if (firebaseUser != null) {
                        Log.d("SignUp", "Sign Up successfully")
                        it.resume(firebaseUser)
                    } else {
                        it.resumeWithException(SuchEmailAlreadyRegisteredException("Such email already registered"))
                    }
                } else {
                    Log.e("SignUp", "Sign up failed", task.exception)
                    it.resumeWithException(RegistrationFailedException("Registration failed"))
                }
            }
    }
}
