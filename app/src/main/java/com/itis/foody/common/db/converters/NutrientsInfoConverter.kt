package com.itis.foody.common.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis.foody.common.db.entities.NutrientsInfo
import javax.inject.Inject

class NutrientsInfoConverter {

    @Inject
    lateinit var gson: Gson

    private val type = object : TypeToken<NutrientsInfo>() {}.type

    @TypeConverter
    fun serialize(info: NutrientsInfo): String =
        gson.toJson(info, type)

    @TypeConverter
    fun deserialize(infoString: String): NutrientsInfo =
        gson.fromJson(infoString, type)
}
