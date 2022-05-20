package com.itis.foody.features.recipe.domain.utils

import javax.inject.Inject

private const val INGREDIENT_IMAGE_PREFIX = "https://spoonacular.com/cdn/ingredients_100x100/"
private const val RECIPE_IMAGE_PREFIX = "https://spoonacular.com/recipeImages/"
private const val RECIPE_IMAGE_SUFFIX = "-312x231.jpg"

class RecipeDataConverter @Inject constructor() {

    fun convertIngredientImageUrl(fileName: String) = INGREDIENT_IMAGE_PREFIX + fileName

    fun convertRecipeImageUrl(id: Int) = RECIPE_IMAGE_PREFIX + id + RECIPE_IMAGE_SUFFIX

    fun convertIngredientAmountToString(amount: Double, unit: String): String =
        amount.toInt().toString() + unit
}
