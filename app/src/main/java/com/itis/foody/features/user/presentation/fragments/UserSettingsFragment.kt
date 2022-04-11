package com.itis.foody.features.user.presentation.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.databinding.FragmentUserSettingsBinding
import com.itis.foody.common.extensions.navigateBack
import com.itis.foody.common.extensions.setBackButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSettingsFragment : Fragment(R.layout.fragment_user_settings) {

    private lateinit var binding: FragmentUserSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserSettingsBinding.bind(view)

        setActionBarAttrs()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            navigateBack()
            true
        }
        R.id.save -> processUserInfo()
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_settings_menu, menu)
    }

    private fun processUserInfo(): Boolean {
        TODO()
    }

    private fun setActionBarAttrs() {
        setBackButton()
//        setTitle()
    }
}
