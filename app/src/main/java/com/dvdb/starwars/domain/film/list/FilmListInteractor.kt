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
            .flatMap { swapiFilmResponse ->
                repository.getFilm(
                    swapiFilmResponse.title,
                    swapiFilmResponse.releaseDate.substring(0, 4).toInt()
                ).map {
                    filmItemFactory.create(
                        swapiFilmResponse.title,
                        swapiFilmResponse.releaseDate,
                        swapiFilmResponse.director,
                        swapiFilmResponse.producer,
                        it.posterPath
                    )
                }.toObservable()
            }.sorted { f1, f2 -> f1.releaseDate.compareTo(f2.releaseDate) }
            .toList()
    }
}