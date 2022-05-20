package com.itis.foody.features.recipe.presentation.search.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itis.foody.features.recipe.domain.models.RecipeSimple
import com.itis.foody.features.recipe.domain.usecases.search.GetRecipeListByIngredientUseCase
import com.itis.foody.features.recipe.domain.usecases.search.GetRecipeListByNameUseCase
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
            runCatching {
                getRecipeListByIngredientUseCase(ingredient)
            }.onSuccess {
                _recipesByIngredient.value = Result.success(it)
            }.onFailure {
                _recipesByIngredient.value = Result.failure(it)
            }
        }
    }

    fun getRecipeListByName(name: String) {
        viewModelScope.launch {
            runCatching {
                getRecipeListByNameUseCase(name)
            }.onSuccess {
                _recipesByName.value = Result.success(it)
            }.onFailure {
                _recipesByName.value = Result.failure(it)
            }
        }
    }
}
