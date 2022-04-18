package com.itis.foody.features.signin.presenttion.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.databinding.FragmentLoginBinding
import com.itis.foody.common.extensions.*
import com.itis.foody.common.utils.ResourceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var resourceManager: ResourceManager
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

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
        with(binding) {
            btnSignIn.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_profileFragment,
                    null
                )
            }
            tvRegistration.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }

    private fun setActionBarAttrs() {
        showActionBar()
        setBackButton()
        setTitle(resourceManager.getString(R.string.log_in))
    }
}
