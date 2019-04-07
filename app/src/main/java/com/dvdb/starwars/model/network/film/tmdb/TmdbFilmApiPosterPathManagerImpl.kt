package com.dvdb.starwars.model.network.film.tmdb

import android.net.Uri

private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w400/"

class TmdbFilmApiPosterPathManagerImpl : TmdbFilmApiPosterPathManager {

    override fun getFullPosterPathUri(posterPath: String): Uri =
        Uri.parse(BASE_POSTER_PATH + posterPath)
}