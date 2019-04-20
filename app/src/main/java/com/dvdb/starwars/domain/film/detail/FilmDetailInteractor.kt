package com.dvdb.starwars.domain.film.detail

import android.net.Uri
import com.dvdb.starwars.common.model.FilmDetail
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmResponse
import com.dvdb.starwars.model.network.film.tmdb.response.TmdbFilmSearchResponse
import com.dvdb.starwars.model.repository.StarWarsRepository
import io.reactivex.Observable
import io.reactivex.Single

internal class FilmDetailInteractor(
        private val repository: StarWarsRepository
) : FilmDetailUseCases {

    override fun getFilmDetail(title: String): Single<FilmDetail> {
        return repository.getFilms()
            .toObservable()
            .flatMapIterable { it }
            .filter { response -> response.title == title }
            .flatMap { filmResponse ->
                repository.getFilm(filmResponse.title, filmResponse.releaseDate.substring(0, 4).toInt())
                    .flatMap { getCharactersAndBuildFilmDetail(filmResponse, it) }
                    .toObservable()
            }
            .singleOrError()
    }

    private fun getCharactersAndBuildFilmDetail(
            filmResponse: SwapiFilmResponse, filmSearchResponse: TmdbFilmSearchResponse
    ): Single<FilmDetail> {
        return Observable.fromIterable(filmResponse.characters)
            .flatMap { character ->
                repository.getPerson(Uri.parse(character).lastPathSegment!!.toInt())
                    .map { filmPersonResponse -> filmPersonResponse.name }
                    .toObservable()
            }
            .toList()
            .map { characters ->
                FilmDetail(
                    filmResponse.title,
                    filmResponse.releaseDate,
                    (filmSearchResponse.voteAverage.toInt() / 2),
                    characters,
                    filmResponse.openingCrawl
                )
            }
    }
}