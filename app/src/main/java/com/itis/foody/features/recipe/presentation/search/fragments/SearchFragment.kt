package com.itis.foody.features.recipe.presentation.search.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.common.utils.ResourceManager
import com.itis.foody.databinding.FragmentSearchBinding
import com.itis.foody.features.recipe.domain.models.RecipeSimple
import com.itis.foody.features.recipe.domain.tempData.LastSeenRepository
import com.itis.foody.features.recipe.domain.tempData.RecipeTagRepository
import com.itis.foody.features.recipe.presentation.rv.popularRecipes.PopularRecipesAdapter
import com.itis.foody.features.recipe.presentation.rv.recipes.RecipeAdapter
import com.itis.foody.features.recipe.presentation.search.viewModels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var resourceManager: ResourceManager

    private lateinit var binding: FragmentSearchBinding
    private lateinit var recipeSearchAdapter: RecipeAdapter
    private lateinit var popularRecipesAdapter: PopularRecipesAdapter
    private lateinit var lastSeenRecipesAdapter: RecipeAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initAdapters()
        initRecyclerViews()
        initSearchView()
        initObservers()
        loadLastSeenRecipes()
    }

    private fun initRecyclerViews() {
        with(binding) {
            rvLastSeen.apply {
                adapter = lastSeenRecipesAdapter
            }
            rvRecipeSets.apply {
                adapter = popularRecipesAdapter
            }
            rvResults.apply {
                adapter = recipeSearchAdapter
            }
        }
    }

    private fun initAdapters() {
        recipeSearchAdapter = RecipeAdapter {
            showRecipe(it)
        }
        lastSeenRecipesAdapter = RecipeAdapter {
            navigateToRecipeListFragment(it)
        }
        popularRecipesAdapter = PopularRecipesAdapter(RecipeTagRepository.recipeTags) {
            navigateToRecipeListFragment(it)
        }
    }

    private fun loadLastSeenRecipes() {
        val list = LastSeenRepository.getList()
        if (list != null) {
            binding.tvLastSeen.visibility = View.VISIBLE
            lastSeenRecipesAdapter.submitList(list)
        } else binding.tvLastSeen.visibility = View.GONE
    }

    private fun initObservers() {
        with(viewModel) {
            recipesByIngredient.observe(viewLifecycleOwner) {
                it.fold(onSuccess = { list ->
                    updateResultList(list)
                    hideLoading()
                }, onFailure = {
                    hideLoading()
                    showErrorText()
                })
            }
            recipesByName.observe(viewLifecycleOwner) {
                it.fold(onSuccess = { list ->
                    updateResultList(list)
                    hideLoading()
                }, onFailure = {
                    hideLoading()
                    showErrorText()
                })
            }
        }
    }

    private fun showErrorText() {
        with(binding) {
            rvResults.visibility = View.INVISIBLE
            tvNoRecipeFound.visibility = View.VISIBLE
        }
    }

    private fun updateResultList(list: List<RecipeSimple>) {
        Log.e("RESULT", list.toString())
        recipeSearchAdapter.submitList(list)
        showResults()
    }

    private fun showResults() {
        binding.rvResults.visibility = View.VISIBLE
    }

    private fun initSearchView() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading()
                getRecipeList(query?.trim().toString())
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

    private fun showLoading() {
        with(binding) {
            groupSearch.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun getRecipeList(query: String) {
        when (binding.spinnerSearchBy.selectedItem.toString()) {
            "Name" -> searchRecipesByName(query)
            "Ingredient" -> searchRecipesByIngredient(query)
        }
    }

    private fun searchRecipesByIngredient(ingredient: String) {
        viewModel.getRecipeListByIngredient(ingredient)
    }

    private fun searchRecipesByName(name: String) {
        viewModel.getRecipeListByName(name)
    }

    private fun showRecipe(id: Int) {
        navigateToRecipeFragment(id)
    }

    private fun navigateToRecipeFragment(id: Int) {
        findNavController().navigate(
            R.id.action_searchFragment_to_detailRecipeFragment,
            Bundle().apply {
                putInt("RECIPE_ID", id)
            }
        )
    }

    private fun navigateToRecipeListFragment(id: Int) {
        findNavController().navigate(
            R.id.action_searchFragment_to_recipeListFragment,
            Bundle().apply {
                putInt("TAG", id)
            }
        )
    }
}
