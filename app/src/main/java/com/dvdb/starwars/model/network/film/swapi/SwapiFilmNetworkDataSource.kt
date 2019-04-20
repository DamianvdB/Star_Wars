package com.dvdb.starwars.model.network.film.swapi

import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmPersonResponse
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmResponse
import io.reactivex.Single

internal interface SwapiFilmNetworkDataSource {
    fun getFilms(): Single<List<SwapiFilmResponse>>
    fun getPerson(
        personIndex: Int
    ): Single<SwapiFilmPersonResponse>
}