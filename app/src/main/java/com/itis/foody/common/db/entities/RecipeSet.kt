package com.itis.foody.common.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_set")
data class RecipeSet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipeSetId")
    var id: Int,
    var userId: Int,
    var name: String
)
