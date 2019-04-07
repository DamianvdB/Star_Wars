package com.dvdb.starwars.common.model

import android.net.Uri
import com.dvdb.starwars.model.network.film.tmdb.TmdbFilmApiPosterPathManager

class FilmListItem private constructor(
    val title: String,
    val releaseDate: String,
    val director: String,
    val producer: String,
    val filmUrl: Uri
) {

    class Factory(
        private val posterPathManager: TmdbFilmApiPosterPathManager
    ) {
        fun create(
            title: String,
            releaseDate: String,
            director: String,
            producer: String,
            posterPath: String
        ): FilmListItem {
            return FilmListItem(
                title,
                releaseDate,
                director,
                producer,
                posterPathManager.getFullPosterPathUri(posterPath)
            )
        }
    }
}
