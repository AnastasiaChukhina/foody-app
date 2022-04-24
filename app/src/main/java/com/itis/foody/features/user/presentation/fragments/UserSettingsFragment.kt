package com.itis.foody.features.user.presentation.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.foody.R
import com.itis.foody.common.extensions.navigateBack
import com.itis.foody.databinding.FragmentUserSettingsBinding
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
