package com.itis.foody.features.recipe.presentation.recipe.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.common.extensions.navigateBack
import com.itis.foody.databinding.FragmentSavedRecipesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedRecipesFragment : Fragment(R.layout.fragment_saved_recipes) {

    private lateinit var binding: FragmentSavedRecipesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedRecipesBinding.bind(view)

        setActionBarAttrs()
    }

    private fun setActionBarAttrs() {
        with(binding) {
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            toolbar.setNavigationOnClickListener {
                navigateBack()
            }
        }
    }
}
