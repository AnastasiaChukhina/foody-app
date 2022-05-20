package com.itis.foody.features.recipe.domain.repositories

import com.itis.foody.features.recipe.domain.models.RecipeSimple

interface RecipeSearchRepository {
    suspend fun getRecipeListByIngredient(ingredient: String): MutableList<RecipeSimple>
    suspend fun getRecipeListByName(name: String): MutableList<RecipeSimple>
}
