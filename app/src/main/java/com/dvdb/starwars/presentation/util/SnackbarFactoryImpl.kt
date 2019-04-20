package com.dvdb.starwars.presentation.util

import android.view.View
import androidx.core.content.ContextCompat
import com.dvdb.starwars.R
import com.google.android.material.snackbar.Snackbar

internal class SnackbarFactoryImpl : SnackbarFactory {

    override fun getSnackbarWithAction(
        parentView: View,
        text: String,
        length: Int,
        actionText: String,
        actionOnClickListener: View.OnClickListener
    ): Snackbar {
        return Snackbar.make(parentView, text, length).apply {
            setAction(actionText, actionOnClickListener.also { dismiss() })
            setActionTextColor(ContextCompat.getColor(parentView.context, R.color.md_red_500))
        }
    }
}