package com.itis.foody.features.recipe.presentation.recipe.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.databinding.FragmentRecipeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment: Fragment(R.layout.fragment_recipe_list) {

    private lateinit var binding: FragmentRecipeListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipeListBinding.bind(view)
    }
}
