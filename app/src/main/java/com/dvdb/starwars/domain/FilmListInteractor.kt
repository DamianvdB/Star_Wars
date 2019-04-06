package com.dvdb.starwars.domain

import android.net.Uri
import com.dvdb.starwars.common.model.FilmListItem
import com.dvdb.starwars.model.repository.StarWarsRepository
import io.reactivex.Single

internal class FilmListInteractor(
    private val repository: StarWarsRepository
) : FilmListUseCases {

    override fun getFilmListItems(): Single<List<FilmListItem>> {
        return repository.getFilms()
            .map { filmResponses ->
                filmResponses.map {
                    FilmListItem(
                        it.title,
                        it.releaseDate,
                        it.director,
                        it.producer,
                        Uri.EMPTY
                    )
                }
            }
    }
}