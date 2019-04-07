package com.dvdb.starwars.model.repository

import com.dvdb.starwars.model.network.film.swapi.SwapiFilmNetworkDataSource
import com.dvdb.starwars.model.network.film.tmdb.TmdbFilmNetworkDataSource

internal class StarWarsRepositoryImpl(
    private val swapiFilmNetworkDataSource: SwapiFilmNetworkDataSource,
    private val tmdbFilmNetworkDataSource: TmdbFilmNetworkDataSource
) : StarWarsRepository {

    override fun getFilms() = swapiFilmNetworkDataSource.getFilms()

    override fun getFilm(title: String, yearReleased: Int) =
        tmdbFilmNetworkDataSource.getFilm(title, yearReleased)
}