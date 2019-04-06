package com.dvdb.starwars.model.repository

import com.dvdb.starwars.model.network.film.FilmNetworkDataSource

internal class StarWarsRepositoryImpl(
    private val filmNetworkDataSource: FilmNetworkDataSource
) : StarWarsRepository {

    override fun getFilms() = filmNetworkDataSource.getFilms()
}