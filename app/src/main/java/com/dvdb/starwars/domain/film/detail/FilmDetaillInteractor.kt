package com.dvdb.starwars.domain.film.detail

import com.dvdb.starwars.common.model.FilmDetail
import com.dvdb.starwars.model.repository.StarWarsRepository
import io.reactivex.Single

internal class FilmDetaillInteractor(
    private val repository: StarWarsRepository
) : FilmDetailUseCases {

    override fun getFilmDetail(title: String): Single<FilmDetail> {
        return repository.getFilms()
            .toObservable()
            .flatMapIterable { it }
            .filter{response -> response.title.equals(title)}
            .flatMap { filmResponse ->
                repository.getFilm(
                    filmResponse.title,
                    filmResponse.releaseDate.substring(0, 4).toInt()
                ).map {
                    FilmDetail(
                        filmResponse.title,
                        filmResponse.releaseDate,
                        (it.voteAverage.toInt() / 2),
                        filmResponse.characters,
                        filmResponse.openingCrawl
                    )
                }.toObservable()
            }.singleOrError()
    }
}