package com.dvdb.starwars.domain

import com.dvdb.starwars.common.model.FilmListItem
import io.reactivex.Single

interface FilmListUseCases {
    fun getFilmListItems(): Single<List<FilmListItem>>
}