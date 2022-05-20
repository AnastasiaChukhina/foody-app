package com.itis.foody.common.db.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class ImageConverter {

    @TypeConverter
    fun serialize(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun deserialize(byteArray: ByteArray): Bitmap =
        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}
