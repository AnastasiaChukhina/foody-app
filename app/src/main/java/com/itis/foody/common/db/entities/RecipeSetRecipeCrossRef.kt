package com.itis.foody.common.db.entities

import androidx.room.Entity

@Entity(
    primaryKeys = ["recipeSetId", "recipeId"]
)
data class RecipeSetRecipeCrossRef(
    val recipeSetId: Int,
    val recipeId: Int
)
