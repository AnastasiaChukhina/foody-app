package com.itis.foody.features.user.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.databinding.FragmentWelcomeBinding
import com.itis.foody.common.extensions.hideActionBar
import com.itis.foody.common.utils.ResourceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

        setListeners()
        hideActionBar()
    }

    private fun setListeners() {
        with(binding) {
            btnSignIn.setOnClickListener {
                findNavController().navigate(
                    R.id.action_welcomeFragment_to_loginFragment,
                    null
                )
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(
                    R.id.action_welcomeFragment_to_registrationFragment,
                    null
                )
            }
        }
    }
}
