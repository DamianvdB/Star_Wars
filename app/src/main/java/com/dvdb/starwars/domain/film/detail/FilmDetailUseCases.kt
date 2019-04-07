package com.dvdb.starwars.domain.film.detail

import com.dvdb.starwars.common.model.FilmDetail
import io.reactivex.Single

interface FilmDetailUseCases {
    fun getFilmDetail(title: String): Single<FilmDetail>
}