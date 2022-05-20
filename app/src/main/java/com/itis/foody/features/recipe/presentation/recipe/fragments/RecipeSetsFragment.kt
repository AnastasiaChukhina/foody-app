package com.itis.foody.features.recipe.presentation.recipe.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.common.utils.ResourceManager
import com.itis.foody.databinding.FragmentRecipeSetsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeSetsFragment : Fragment(R.layout.fragment_recipe_sets) {

    @Inject
    lateinit var resourceManager: ResourceManager
    private lateinit var binding: FragmentRecipeSetsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeSetsBinding.bind(view)
    }
}
