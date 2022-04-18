package com.itis.foody.features.signup.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.common.db.dao.UserDao
import com.itis.foody.common.db.entities.User
import com.itis.foody.features.signup.domain.exceptions.FirebaseUserNotFound
import com.itis.foody.features.signup.domain.exceptions.SuchEmailAlreadyRegistered
import com.itis.foody.features.signup.domain.models.UserForm
import com.itis.foody.features.signup.domain.repositories.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userDao: UserDao
) : SignUpRepository {

    override suspend fun registerUser(user: UserForm): FirebaseUser {
        if (ifSuchUserAlreadyExist(user) != true) {
            saveToDatabase(user)
            saveToCloud(user)
            return auth.currentUser ?: throw FirebaseUserNotFound("Not Found")
        } else throw SuchEmailAlreadyRegistered("Such email already registered")
    }

    private fun ifSuchUserAlreadyExist(user: UserForm): Boolean? {
        var isNewUser: Boolean? = null
        auth.fetchSignInMethodsForEmail(user.email)
            .addOnCompleteListener {
                isNewUser = it.result.signInMethods?.isEmpty()
            }
        return isNewUser
    }

    private suspend fun saveToDatabase(user: UserForm) {
        userDao.save(User(0, user.username, user.email, user.password))
    }

    private fun saveToCloud(user: UserForm) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SignUp", "Sign Up successfully")
//                    task.addOnCompleteListener {
//                        firebaseUser = auth.currentUser
//                    }
                } else {
                    Log.e("SignUp", "Sign up failed", task.exception)
                }
            }
    }
}
