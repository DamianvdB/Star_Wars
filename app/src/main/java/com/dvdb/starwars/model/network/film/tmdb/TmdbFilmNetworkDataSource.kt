package com.dvdb.starwars.model.network.film.tmdb

import com.dvdb.starwars.model.network.film.tmdb.response.TmdbFilmSearchResponse
import io.reactivex.Single

internal interface TmdbFilmNetworkDataSource {
    fun getFilm(
        title: String,
        yearReleased: Int
    ): Single<TmdbFilmSearchResponse>
}