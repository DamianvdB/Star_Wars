package com.dvdb.starwars.model.repository

import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmPersonResponse
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmResponse
import com.dvdb.starwars.model.network.film.tmdb.response.TmdbFilmSearchResponse
import io.reactivex.Single

internal interface StarWarsRepository {

    fun getFilms(): Single<List<SwapiFilmResponse>>

    fun getFilm(
        title: String,
        yearReleased: Int
    ): Single<TmdbFilmSearchResponse>

    fun getPerson(
        personIndex: Int
    ): Single<SwapiFilmPersonResponse>
}