package com.dvdb.starwars.model.network.film

import com.dvdb.starwars.model.network.film.response.FilmReponse
import io.reactivex.Single

interface FilmNetworkDataSource {
    fun getFilms(): Single<List<FilmReponse>>
}