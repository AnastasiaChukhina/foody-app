package com.itis.foody.common.db

data class RecipeSet(
    var id: String,
    var name: String,
    var recipes: MutableMap<String, Any>
)
