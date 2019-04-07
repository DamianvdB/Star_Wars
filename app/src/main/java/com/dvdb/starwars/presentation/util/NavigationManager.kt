package com.dvdb.starwars.presentation.util

import android.content.Intent

interface NavigationManager {
    fun getFilmActivityIntent(): Intent
}