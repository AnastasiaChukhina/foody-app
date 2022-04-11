package com.itis.foody.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.itis.foody.R
import com.itis.foody.extensions.navigateBack
import com.itis.foody.extensions.setBackButton
import com.itis.foody.extensions.setTitle

class NotificationsSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBarAttrs()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.notifications_preferences, rootKey)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            navigateBack()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setActionBarAttrs() {
        setBackButton()
//        setTitle()
    }
}
