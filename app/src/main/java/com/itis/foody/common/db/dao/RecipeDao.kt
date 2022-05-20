package com.itis.foody.common.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.itis.foody.common.db.entities.Recipe
import com.itis.foody.common.db.entities.SetWithRecipes

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe_set WHERE recipeSetId=:recipeSetId")
    suspend fun getAllRecipesInSet(recipeSetId: Int): List<SetWithRecipes>

    @Query("SELECT * FROM recipe WHERE recipeId=:recipeId")
    suspend fun getRecipeById(recipeId: Int): Recipe

    @Query("DELETE from recipe WHERE recipeId=:recipeId")
    suspend fun deleteRecipeById(recipeId: Int)
}
