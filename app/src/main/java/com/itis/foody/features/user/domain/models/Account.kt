package com.itis.foody.features.user.domain.models

import android.graphics.Bitmap

data class Account(
    var id: Int,
    var username: String,
    var email: String,
    var profileImage: Bitmap?
)
