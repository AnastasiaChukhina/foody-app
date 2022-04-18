package com.itis.foody.common.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SetWithRecipes(
    @Embedded
    val recipeSet: RecipeSet,
    @Relation(
        parentColumn = "recipeSetId",
        entityColumn = "recipeId",
        associateBy = Junction(RecipeSetRecipeCrossRef::class)
    )
    val recipes: List<Recipe>
)
