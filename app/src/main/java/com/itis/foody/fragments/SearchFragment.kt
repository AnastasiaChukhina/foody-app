package com.itis.foody.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.databinding.FragmentSearchBinding
import com.itis.foody.extensions.hideBackButton

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        setActionBarAttrs()
    }

    private fun setActionBarAttrs() {
        hideBackButton()
//        setTitle()
    }
}
