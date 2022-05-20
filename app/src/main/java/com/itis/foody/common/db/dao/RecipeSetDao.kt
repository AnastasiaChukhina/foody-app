package com.itis.foody.common.db.dao

import androidx.room.*
import com.itis.foody.common.db.entities.RecipeSet
import com.itis.foody.common.db.entities.UserAndAllRecipeSets

@Dao
interface RecipeSetDao {

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getRecipeSetsByUserId(userId: Int): UserAndAllRecipeSets

    @Insert
    fun save(recipeSet: RecipeSet)

    @Update
    fun update(recipeSet: RecipeSet)

    @Query("DELETE from recipe_set where recipeSetId=:recipeSetId")
    fun deleteRecipeSetById(recipeSetId: Int)
}
