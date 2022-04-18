package com.itis.foody.features.recipe.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.databinding.FragmentRecipeSetsBinding
import com.itis.foody.common.extensions.hideBackButton
import com.itis.foody.common.extensions.setTitle
import com.itis.foody.common.utils.ResourceManager
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

        setActionBarAttrs()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_recipeSetsFragment_to_savedRecipesFragment)
        }
    }

    private fun setActionBarAttrs() {
        hideBackButton()
        setTitle(resourceManager.getString(R.string.saved_recipes))
    }
}
