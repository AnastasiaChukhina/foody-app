package com.itis.foody.features.user.presentation.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itis.foody.R
import com.itis.foody.common.exceptions.InvalidEmailException
import com.itis.foody.common.exceptions.InvalidUsernameException
import com.itis.foody.common.extensions.*
import com.itis.foody.databinding.FragmentUserSettingsBinding
import com.itis.foody.features.user.domain.models.Account
import com.itis.foody.features.user.presentation.viewModels.UserViewModel
import com.itis.foody.features.user.domain.service.UserDataValidationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserSettingsFragment : Fragment(R.layout.fragment_user_settings) {

    private lateinit var binding: FragmentUserSettingsBinding
    private lateinit var user: Account

    @Inject
    lateinit var userDataValidationService: UserDataValidationService

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserSettingsBinding.bind(view)

        showDataLoading()
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
                hideDataLoading()
            }, onFailure = {
                showMessage("Problems. Try again.")
            })
        }
        viewModel.updatedUser.observe(viewLifecycleOwner) {
            it.fold(onSuccess = {
                showMessage("Data successfully updated")
                navigateBack()
                hideLoading()
            }, onFailure = {
                showMessage("Problems. Please, try again.")
                hideLoading()
            })
        }
    }

    private fun updateUI(user: Account) {
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
        if (isEmailValid(email) && isUsernameValid(username) &&
            (email != user.email || username != user.username)
        ) {
            showPasswordAlertDialog(username, email)
        }
        else navigateBack()
    }

    private fun showPasswordAlertDialog(username: String, email: String) {
        val view = layoutInflater.inflate(R.layout.dialog_password, null)
        val text = view.findViewById<EditText>(R.id.et_pass)

        AlertDialog.Builder(requireContext())
            .setMessage("Enter your password:")
            .setView(view)
            .setPositiveButton("Send") { _, _ ->
                checkInput(username, email, text.text.toString())
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun checkInput(username: String, email: String, password: String) {
        if(password.isNotBlank()) try {
            viewModel.changeUserData(username, email, password)
            showLoading()
        } catch (e: Exception) {
            showMessage("Wrong password")
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
