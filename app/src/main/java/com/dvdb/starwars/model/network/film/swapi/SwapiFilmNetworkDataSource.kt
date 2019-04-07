package com.dvdb.starwars.model.network.film.swapi

import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmResponse
import io.reactivex.Single

interface SwapiFilmNetworkDataSource {
    fun getFilms(): Single<List<SwapiFilmResponse>>
}