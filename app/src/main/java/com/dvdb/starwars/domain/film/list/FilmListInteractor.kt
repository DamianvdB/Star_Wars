package com.dvdb.starwars.domain.film.list

import com.dvdb.starwars.common.model.FilmListItem
import com.dvdb.starwars.model.repository.StarWarsRepository
import io.reactivex.Single

internal class FilmListInteractor(
    private val repository: StarWarsRepository,
    private val filmItemFactory: FilmListItem.Factory
) : FilmListUseCases {

    override fun getFilmListItems(): Single<List<FilmListItem>> {
        return repository.getFilms()
            .toObservable()
            .flatMapIterable { it }
            .flatMap { filmResponse ->
                repository.getFilm(
                    filmResponse.title,
                    filmResponse.releaseDate.substring(0, 4).toInt()
                ).map {
                    filmItemFactory.create(
                        filmResponse.title,
                        filmResponse.releaseDate,
                        filmResponse.director,
                        filmResponse.producer,
                        it.posterPath
                    )
                }.toObservable()
            }.sorted { f1, f2 -> f1.releaseDate.compareTo(f2.releaseDate) }
            .toList()
    }
}