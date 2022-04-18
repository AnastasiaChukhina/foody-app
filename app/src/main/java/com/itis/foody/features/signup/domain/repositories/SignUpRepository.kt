package com.itis.foody.features.signup.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.itis.foody.features.signup.domain.models.UserForm

interface SignUpRepository {
    suspend fun registerUser(user: UserForm): FirebaseUser
}
