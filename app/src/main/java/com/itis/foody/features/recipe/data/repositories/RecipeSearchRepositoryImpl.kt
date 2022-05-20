package com.itis.foody.features.recipe.data.repositories

import com.itis.foody.features.recipe.data.api.Api
import com.itis.foody.features.recipe.data.response.popularRecipes.PopularRecipesResponse
import com.itis.foody.features.recipe.data.response.recipeInfo.RecipeInfoResponse
import com.itis.foody.features.recipe.data.response.searchByIngredient.RecipeListByIngredientResponse
import com.itis.foody.features.recipe.data.response.searchByName.RecipeListByNameResponse
import com.itis.foody.features.recipe.data.response.similarRecipes.SimilarRecipesResponse
import com.itis.foody.features.recipe.domain.mappers.ModelMapper
import com.itis.foody.features.recipe.domain.models.RecipeDetails
import com.itis.foody.features.recipe.domain.models.RecipeSimple
import com.itis.foody.features.recipe.domain.repositories.RecipeSearchRepository
import javax.inject.Inject

class RecipeSearchRepositoryImpl @Inject constructor(
    private val api: Api,
    private val recipeListByIngredientMapper: ModelMapper<RecipeListByIngredientResponse, MutableList<RecipeSimple>>,
    private val recipeListByNameMapper: ModelMapper<RecipeListByNameResponse, MutableList<RecipeSimple>>
) : RecipeSearchRepository {

    override suspend fun getRecipeListByIngredient(ingredient: String): MutableList<RecipeSimple> =
        recipeListByIngredientMapper.map(
            api.getRecipesByIngredients(ingredient)
        )

    override suspend fun getRecipeListByName(name: String): MutableList<RecipeSimple> =
        recipeListByNameMapper.map(
            api.getRecipesByName(name)
        )
}
