package com.itis.foody.common.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itis.foody.MainActivity

fun Fragment.setTitle(
    name: String
) {
    (activity as MainActivity).supportActionBar?.apply {
        title = name
    }
}

fun Fragment.showActionBar() {
    (activity as MainActivity).supportActionBar?.show()
}

fun Fragment.hideActionBar() {
    (activity as MainActivity).supportActionBar?.hide()
}

fun Fragment.setBackButton() {
    (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }
}

fun Fragment.hideBackButton() {
    (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(false)
    }
}

fun Fragment.navigateBack() {
    findNavController().popBackStack()
}
