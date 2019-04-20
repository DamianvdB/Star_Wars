package com.dvdb.starwars.model.repository

import com.dvdb.starwars.model.network.film.swapi.SwapiFilmNetworkDataSource
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmPersonResponse
import com.dvdb.starwars.model.network.film.tmdb.TmdbFilmNetworkDataSource
import io.reactivex.Single

internal class StarWarsRepositoryImpl(
    private val swapiFilmNetworkDataSource: SwapiFilmNetworkDataSource,
    private val tmdbFilmNetworkDataSource: TmdbFilmNetworkDataSource,
    private val retriesOnError: Long
) : StarWarsRepository {

    override fun getFilms() = swapiFilmNetworkDataSource.getFilms().retry(retriesOnError)

    override fun getFilm(title: String, yearReleased: Int) =
        tmdbFilmNetworkDataSource.getFilm(title, yearReleased).retry(retriesOnError)

    override fun getPerson(personIndex: Int): Single<SwapiFilmPersonResponse> =
        swapiFilmNetworkDataSource.getPerson(personIndex).retry(retriesOnError)
}