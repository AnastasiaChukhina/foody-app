package com.itis.foody.features.user.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.itis.foody.R
import com.itis.foody.common.extensions.hideBackButton
import com.itis.foody.common.extensions.showMessage
import com.itis.foody.databinding.FragmentProfileBinding
import com.itis.foody.features.user.presentation.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        setActionBarAttrs()
        setListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner){
            it.fold(onSuccess = {
                navigateToHelloFragment()
            }, onFailure = {
                showMessage("Problems with logout. please, try again.")
            })
        }
    }

    private fun setActionBarAttrs() {
        hideBackButton()
//        setTitle()
    }

    private fun setListeners() {
        with(binding) {
            clLogout.setOnClickListener {
                showLogoutDialog()
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

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("Do you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                logout()
            }
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun logout() {
        viewModel.logout()
    }

    private fun navigateToHelloFragment() {
        findNavController().navigate(
            R.id.action_profileFragment_to_welcomeFragment
        )
    }
}
