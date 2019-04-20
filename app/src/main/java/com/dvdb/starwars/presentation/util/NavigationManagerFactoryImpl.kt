package com.dvdb.starwars.presentation.util

import android.content.Context
import androidx.fragment.app.FragmentManager

internal class NavigationManagerFactoryImpl(
        private val context: Context
) : NavigationManager.Factory {

    override fun create(supportFragmentManager: FragmentManager): NavigationManager {
        return NavigationManagerImpl(context, supportFragmentManager)
    }
}