package com.itis.foody.common.db.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user",
    indices = [
        Index("email", unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username: String,
    var email: String,
    var password: String,
    var profileImage: Bitmap?
)
