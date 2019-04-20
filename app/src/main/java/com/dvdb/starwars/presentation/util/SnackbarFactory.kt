package com.dvdb.starwars.presentation.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

internal interface SnackbarFactory {
    fun getSnackbarWithAction(
        parentView: View,
        text: String,
        length: Int = Snackbar.LENGTH_INDEFINITE,
        actionText: String,
        actionOnClickListener: View.OnClickListener
    ): Snackbar
}