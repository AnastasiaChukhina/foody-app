package com.itis.foody.common.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis.foody.common.db.entities.RecipeStep
import javax.inject.Inject

class RecipeStepConverter {

    @Inject
    lateinit var gson: Gson

    private val type = object : TypeToken<List<RecipeStep>>() {}.type

    @TypeConverter
    fun serialize(list: List<RecipeStep>): String =
        gson.toJson(list, type)

    @TypeConverter
    fun deserialize(recipeSteps: String): List<RecipeStep> =
        gson.fromJson(recipeSteps, type)
}
