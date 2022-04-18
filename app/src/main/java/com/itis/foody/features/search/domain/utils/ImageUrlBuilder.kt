package com.itis.foody.features.search.domain.utils

private const val INGREDIENT_IMAGE_PREFIX = "https://spoonacular.com/cdn/ingredients_100x100/"

class ImageUrlBuilder {
    fun buildIngredientImageUrl(fileName: String) = INGREDIENT_IMAGE_PREFIX + fileName
}
