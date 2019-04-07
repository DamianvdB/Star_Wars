package com.dvdb.starwars.model.network.film.tmdb

import com.dvdb.starwars.model.network.film.tmdb.response.TmdbFilmSearchResponse
import io.reactivex.Single

internal class TmdbFilmNetworkDataSourceImpl(
    private val apiService: TmdbApiService
) : TmdbFilmNetworkDataSource {

    private val films: MutableMap<String, TmdbFilmSearchResponse> = mutableMapOf()

    override fun getFilm(
        title: String,
        yearReleased: Int
    ): Single<TmdbFilmSearchResponse> {
        return if (films.containsKey(title)) {
            Single.just(films[title])
        } else {
            apiService.getFilm(title, yearReleased).map {
                films[title] = it.results[0]
                return@map it.results[0]
            }
        }
    }
}