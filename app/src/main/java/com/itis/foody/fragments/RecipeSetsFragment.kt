package com.itis.foody.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.databinding.FragmentRecipeSetsBinding
import com.itis.foody.extensions.hideBackButton

class RecipeSetsFragment : Fragment(R.layout.fragment_recipe_sets) {

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
//        setTitle()
    }
}
