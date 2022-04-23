package com.itis.foody.features.signin.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itis.foody.features.signin.domain.exceptions.FirebaseAuthFailedException
import com.itis.foody.features.signin.domain.exceptions.UnknownEmailException
import com.itis.foody.features.signin.domain.models.UserForm
import com.itis.foody.features.signin.domain.repositories.SignInRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignInRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SignInRepository {

    override suspend fun auth(user: UserForm): FirebaseUser = suspendCoroutine {
        try {
            auth.signInWithEmailAndPassword(user.email, user.password)
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
}
