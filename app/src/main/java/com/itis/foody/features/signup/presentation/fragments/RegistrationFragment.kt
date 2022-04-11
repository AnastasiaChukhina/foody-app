package com.itis.foody.features.signup.presentation.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.common.extensions.*
import com.itis.foody.databinding.FragmentRegistrationBinding
import com.itis.foody.common.utils.ResourceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    @Inject
    private lateinit var resourceManager: ResourceManager
    private lateinit var binding: FragmentRegistrationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)

        setActionBarAttrs()
        setListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            hideActionBar()
            navigateBack()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setListeners() {
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_registrationFragment_to_profileFragment,
                null,
                null
            )
        }
    }

    private fun setActionBarAttrs() {
        showActionBar()
        setBackButton()
        setTitle(resourceManager.getString(R.string.registration))
    }
}
