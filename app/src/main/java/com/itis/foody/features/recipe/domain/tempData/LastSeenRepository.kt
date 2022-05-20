package com.itis.foody.features.recipe.domain.tempData

import com.itis.foody.features.recipe.domain.models.RecipeSimple

private const val LIST_SIZE = 10

object LastSeenRepository {
    private val list = arrayOfNulls<RecipeSimple>(LIST_SIZE)

    fun getList(): List<RecipeSimple>? {
        return if (list[0] == null) null
        else list.clone()
            .toList()
            .filterNotNull()
            .shuffled()
    }

    fun addNew(recipeSimple: RecipeSimple) {
        moveElements()
        list[0] = recipeSimple
    }

    private fun moveElements() {
        for (i in list.size downTo 1) {
            list[i] = list[i - 1]
        }
    }
}
