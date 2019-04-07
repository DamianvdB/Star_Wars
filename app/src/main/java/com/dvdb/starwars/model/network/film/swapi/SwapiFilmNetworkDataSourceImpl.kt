package com.dvdb.starwars.model.network.film.swapi

import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmResponse
import io.reactivex.Single

internal class SwapiFilmNetworkDataSourceImpl(
    private val apiService: SwapiFilmApiService
) : SwapiFilmNetworkDataSource {

    private var films: List<SwapiFilmResponse>? = null

    override fun getFilms(): Single<List<SwapiFilmResponse>> {
        return if (films != null && films!!.isNotEmpty()) {
            Single.just(films)
        } else {
            apiService.getFilms().map {
                films = it.results
                return@map it.results
            }
        }
    }
}