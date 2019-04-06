package com.dvdb.starwars.model.network.film

import com.dvdb.starwars.model.network.film.response.FilmReponse
import io.reactivex.Single

internal class FilmNetworkDataSourceImpl(
    private val filmApiService: FilmApiService
) : FilmNetworkDataSource {

    private var films: List<FilmReponse>? = null

    override fun getFilms(): Single<List<FilmReponse>> {
        return if (films != null && films!!.isNotEmpty()) {
            Single.just(films)
        } else {
            filmApiService.getFilms().map {
                films = it.results
                return@map it.results
            }
        }
    }
}