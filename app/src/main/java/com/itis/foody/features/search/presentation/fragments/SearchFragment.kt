package com.itis.foody.features.search.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.databinding.FragmentSearchBinding
import com.itis.foody.common.extensions.hideBackButton
import com.itis.foody.common.extensions.setTitle
import com.itis.foody.common.utils.ResourceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var resourceManager: ResourceManager
    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        setActionBarAttrs()
    }

    private fun setActionBarAttrs() {
        hideBackButton()
        setTitle(resourceManager.getString(R.string.recipe_search))
    }
}
