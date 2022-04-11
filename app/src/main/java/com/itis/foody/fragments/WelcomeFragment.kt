package com.itis.foody.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.databinding.FragmentWelcomeBinding
import com.itis.foody.extensions.hideActionBar

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
