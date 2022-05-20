package com.itis.foody.common.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.itis.foody.R

fun Fragment.navigateBack() {
    findNavController().popBackStack()
}

fun Fragment.showMessage(message: String) {
    Snackbar.make(
        requireActivity().findViewById(R.id.container),
        message,
        Snackbar.LENGTH_LONG
    ).show()
}
