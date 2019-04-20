package com.dvdb.starwars.presentation.util

import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

internal interface NavigationManager {
    fun getFilmActivityIntent(): Intent
    fun getFilmListFragment(): Fragment
    fun getFilmDetailFragment(filmTitle: String): Fragment
    fun isFragmentsEmpty(): Boolean
    fun isFragmentWithTagPresent(fragmentTag: String): Boolean
    fun addFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String)
    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, fragmentTag: String)

    interface Factory {
        fun create(supportFragmentManager: FragmentManager): NavigationManager
    }
}