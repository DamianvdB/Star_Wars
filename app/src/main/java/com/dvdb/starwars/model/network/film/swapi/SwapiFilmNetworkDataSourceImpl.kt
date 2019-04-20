package com.dvdb.starwars.model.network.film.swapi

import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmPersonResponse
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmResponse
import io.reactivex.Single

internal class SwapiFilmNetworkDataSourceImpl(
    private val apiService: SwapiFilmApiService
) : SwapiFilmNetworkDataSource {

    private var films: List<SwapiFilmResponse> = emptyList()
    private var persons: MutableMap<Int, SwapiFilmPersonResponse> = mutableMapOf()

    override fun getFilms(): Single<List<SwapiFilmResponse>> {
        return if (films.isNotEmpty()) {
            Single.just(films)
        } else {
            apiService.getFilms().map {
                films = it.results
                return@map it.results
            }
        }
    }

    override fun getPerson(personIndex: Int): Single<SwapiFilmPersonResponse> {
        return if (persons.containsKey(personIndex)) {
            Single.just(persons[personIndex])
        } else {
            apiService.getPerson(personIndex).map {
                persons[personIndex] = it
                return@map it
            }
        }
    }
}