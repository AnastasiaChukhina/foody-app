package com.itis.foody.features.recipe.presentation.recipe.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.common.extensions.navigateBack
import com.itis.foody.databinding.FragmentDetailRecipeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {

    private lateinit var binding: FragmentDetailRecipeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailRecipeBinding.bind(view)

        setActionBarAttrs()
        setListeners()
    }

    private fun setListeners() {
        binding.btnNutrientsInfo.setOnClickListener {
            findNavController().navigate(
                R.id.action_detailRecipeFragment_to_recipeInfoDialogFragment
            )
        }
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
