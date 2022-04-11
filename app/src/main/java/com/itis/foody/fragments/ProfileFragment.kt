package com.itis.foody.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.databinding.FragmentProfileBinding
import com.itis.foody.extensions.hideBackButton

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        setActionBarAttrs()
        setListeners()
    }

    private fun setActionBarAttrs() {
        hideBackButton()
//        setTitle()
    }

    private fun setListeners() {
        with(binding) {
            clLogout.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_welcomeFragment
                )
            }
            clAccountSettings.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_userSettingsFragment,
                    null
                )
            }
            clNotificationsSettings.setOnClickListener {
                findNavController().navigate(
                    R.id.action_profileFragment_to_notificationsSettingsFragment
                )
            }
        }
    }
}
