package com.itis.foody.features.user.data.repositories

import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.itis.foody.common.db.entities.User
import com.itis.foody.common.di.modules.qualifiers.UsersReference
import com.itis.foody.common.mappers.ModelMapper
import com.itis.foody.features.user.domain.exceptions.UserNotFoundException
import com.itis.foody.features.user.domain.models.Account
import com.itis.foody.features.user.domain.repositories.UserRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val accountMapper: ModelMapper<User, Account>,
    @UsersReference private val reference: DatabaseReference
) : UserRepository {

    override suspend fun getUser(): Account {
        return getCurrentUser()
    }

    override suspend fun changeUserData(
        username: String,
        email: String,
        password: String
    ): Account =
        processUserData(username, email, password)

    override suspend fun logout(): FirebaseUser? {
        auth.signOut()
        return auth.currentUser
    }

    private suspend fun processUserData(
        username: String,
        email: String,
        password: String
    ): Account {
        val firebaseUser = getAuthUser()
        firebaseUser.apply {
            updateUserUsername(username, this.uid)
            if (this.email.toString() != email) {
                verifyAndUpdate(email, password)
            }
        }
        return getCurrentUser()
    }

    private fun updateEmailInCloud(email: String) {
        auth.currentUser?.apply {
            updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("EMAIL CHANGE", "Successfully changed")
                    } else {
                        Log.e("EMAIL CHANGE", "Unsuccessful try")
                    }
                }
        }
    }

    private suspend fun verifyAndUpdate(email: String, password: String) {
        val firebaseUser = getAuthUser()
        val user = getData(firebaseUser)
        val credential = EmailAuthProvider.getCredential(user.email, password)
        firebaseUser.apply {
            reauthenticate(credential)
                .addOnCompleteListener {
                    updateUserEmail(email, this.uid)
                    updateEmailInCloud(email)
                }
        }
    }

    private fun updateUserEmail(email: String, uid: String)  {
        reference.child(uid).child("email").setValue(email)
            .addOnSuccessListener { success ->
                Log.i("EMAIL CHANGE", "Email successfully changed")
            }.addOnFailureListener { e ->
                Log.e("EMAIL CHANGE", "Email change failed")
            }
    }

    private suspend fun updateUserUsername(username: String, uid: String) = suspendCoroutine<Void> {
        reference.child(uid).child("username").setValue(username)
            .addOnSuccessListener { success ->
                Log.i("USERNAME CHANGE", "Username successfully changed")
                it.resume(success)
            }.addOnFailureListener { e ->
                Log.e("USERNAME CHANGE", "Username change failed")
                it.resumeWithException(e)
            }
    }

    private suspend fun getCurrentUser(): Account {
        val firebaseUser = getAuthUser()
        return firebaseUser.let {
            accountMapper.map(
                getData(it)
            )
        }
    }

    private suspend fun getData(firebaseUser: FirebaseUser): User = suspendCoroutine {
        reference.child(firebaseUser.uid).get()
            .addOnSuccessListener { data ->
                data.getValue(User::class.java)?.apply {
                    it.resume(this)
                }
            }.addOnFailureListener {
                throw UserNotFoundException("User not found")
            }
    }

    private fun getAuthUser() = auth.currentUser ?: throw UserNotFoundException("User not found")
}
