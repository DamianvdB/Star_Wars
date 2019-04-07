package com.dvdb.starwars.model.network.film.tmdb

import android.net.Uri

interface TmdbFilmApiPosterPathManager {
    fun getFullPosterPathUri(posterPath: String): Uri
}