package com.dvdb.starwars.presentation.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dvdb.starwars.presentation.film.FilmActivity
import com.dvdb.starwars.presentation.film.detail.FILM_TITLE_BUNDLE_KEY
import com.dvdb.starwars.presentation.film.detail.FilmDetailFragment
import com.dvdb.starwars.presentation.film.list.FilmListFragment

class NavigationManagerImpl(
    private val context: Context,
    private val supportFragmentManager: FragmentManager
) : NavigationManager {

    override fun getFilmActivityIntent(): Intent = Intent(context, FilmActivity::class.java)

    override fun getFilmListFragment(): Fragment = FilmListFragment()

    override fun getFilmDetailFragment(filmTitle: String): Fragment {
        return FilmDetailFragment().apply {
            arguments = Bundle()
            arguments!!.putString(FILM_TITLE_BUNDLE_KEY, filmTitle)
        }
    }

    override fun isFragmentsEmpty(): Boolean {
        return supportFragmentManager.fragments.isEmpty()
    }

    override fun isFragmentWithTagPresent(fragmentTag: String): Boolean {
        return supportFragmentManager.fragments.any { fragment -> fragment.tag == fragmentTag }
    }

    override fun addFragment(containerViewId: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .commit()
    }

    override fun replaceFragment(containerViewId: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager.beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .addToBackStack(null)
            .commit()
    }
}