package com.dvdb.starwars.presentation.util

import android.content.Context
import android.content.Intent
import com.dvdb.starwars.presentation.film.FilmActivity

class NavigationManagerImpl(
    private val context: Context
) : NavigationManager {

    override fun getFilmActivityIntent(): Intent {
        return Intent(context, FilmActivity::class.java)
    }
}