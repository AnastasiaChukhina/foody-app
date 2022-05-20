package com.itis.foody.features.recipe.presentation.search.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.foody.common.utils.SingleLiveEvent
import com.itis.foody.features.recipe.domain.models.RecipeSimple
import com.itis.foody.features.recipe.domain.usecases.search.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipeListByIngredientUseCase: GetRecipeListByIngredientUseCase,
    private val getRecipeListByNameUseCase: GetRecipeListByNameUseCase
) : ViewModel() {

    private var _recipesByName: MutableLiveData<Result<MutableList<RecipeSimple>>> =
        MutableLiveData()
    val recipesByName: LiveData<Result<MutableList<RecipeSimple>>> = _recipesByName

    private var _recipesByIngredient: MutableLiveData<Result<MutableList<RecipeSimple>>> =
        MutableLiveData()
    val recipesByIngredient: LiveData<Result<MutableList<RecipeSimple>>> = _recipesByIngredient

    fun getRecipeListByIngredient(ingredient: String) {
        viewModelScope.launch {
            try {
                val list = getRecipeListByIngredientUseCase(ingredient)
                _recipesByIngredient.value = Result.success(list)
            } catch (e: Exception) {
                _recipesByIngredient.value = Result.failure(e)
            }
        }
    }

    fun getRecipeListByName(name: String) {
        viewModelScope.launch {
            try {
                val list = getRecipeListByNameUseCase(name)
                _recipesByName.value = Result.success(list)
            } catch (e: Exception) {
                _recipesByName.value = Result.failure(e)
            }
        }
    }
}
