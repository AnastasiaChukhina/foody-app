package com.itis.foody.common.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipeId")
    var id: Int,
    var title: String,
    var description: String,
    var ingredients: List<Ingredient>,
    var nutrientsInfo: NutrientsInfo
)
