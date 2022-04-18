package com.itis.foody.common.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis.foody.common.db.entities.Ingredient
import javax.inject.Inject

class IngredientConverter {

    @Inject
    lateinit var gson: Gson

    private val type = object : TypeToken<List<Ingredient>>() {}.type

    @TypeConverter
    fun serialize(list: List<Ingredient>): String =
        gson.toJson(list, type)

    @TypeConverter
    fun deserialize(ingredients: String): List<Ingredient> =
        gson.fromJson(ingredients, type)
}
