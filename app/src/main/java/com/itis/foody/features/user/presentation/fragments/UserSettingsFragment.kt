package com.itis.foody.features.user.presentation.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itis.foody.R
import com.itis.foody.common.db.entities.User
import com.itis.foody.common.exceptions.InvalidEmailException
import com.itis.foody.common.exceptions.InvalidUsernameException
import com.itis.foody.common.extensions.navigateBack
import com.itis.foody.common.extensions.showMessage
import com.itis.foody.databinding.FragmentUserSettingsBinding
import com.itis.foody.features.user.presentation.viewModels.UserViewModel
import com.itis.foody.features.user.service.UserDataValidationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserSettingsFragment : Fragment(R.layout.fragment_user_settings) {

    private lateinit var binding: FragmentUserSettingsBinding
    private lateinit var user: User

    @Inject
    lateinit var userDataValidationService: UserDataValidationService

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserSettingsBinding.bind(view)

        initObservers()
        getUser()
        setActionBarAttrs()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save -> {
            processUserInfo()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_settings_menu, menu)
    }

    private fun initObservers() {
        viewModel.sessionUser.observe(viewLifecycleOwner) {
            it.fold(onSuccess = { user ->
                this.user = user
                updateUI(user)
            }, onFailure = {
                showMessage("Problems. Try again.")
            })
        }
        viewModel.updatedUser.observe(viewLifecycleOwner) {
            it.fold(onSuccess = {
                showMessage("Data successfully updated")
                navigateBack()
            }, onFailure = {
                showMessage("Problems. Please, try again.")
            })
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            etUsername.setText(user.username)
            etEmail.setText(user.email)
        }
    }

    private fun getUser() {
        viewModel.getSessionUser()
    }

    private fun processUserInfo() {
        val email = binding.etEmail.text.toString()
        val username = binding.etUsername.text.toString()
        if (isEmailValid(email) && isUsernameValid(username)) {
            viewModel.changeUserData(username, email)
        }
    }

    private fun isEmailValid(email: String): Boolean =
        try {
            userDataValidationService.validateEmail(email)
            true
        } catch (e: InvalidEmailException) {
            showMessage("Invalid email")
            false
        }

    private fun isUsernameValid(username: String): Boolean =
        try {
            userDataValidationService.validateUsername(username)
            true
        } catch (e: InvalidUsernameException) {
            showMessage("Invalid username: should be 6-20 symbols length")
            false
        }

    private fun setActionBarAttrs() {
        with(binding) {
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            toolbar.setNavigationOnClickListener {
                navigateBack()
            }
            toolbar.setOnMenuItemClickListener {
                onOptionsItemSelected(it)
            }
        }
    }
}
