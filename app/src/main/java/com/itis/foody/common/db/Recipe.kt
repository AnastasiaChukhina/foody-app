package com.itis.foody.common.db

data class Recipe(
    var apiId: Int,
    var title: String,
    var summary: String,
    var ingredients: List<Ingredient>,
    var nutrientsInfo: NutrientsInfo,
    var image: String,
    var recipeSteps: List<RecipeStep>
)
