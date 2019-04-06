package com.dvdb.starwars.model.repository

import com.dvdb.starwars.model.network.film.response.FilmReponse
import io.reactivex.Single

internal interface StarWarsRepository {
    fun getFilms(): Single<List<FilmReponse>>
}