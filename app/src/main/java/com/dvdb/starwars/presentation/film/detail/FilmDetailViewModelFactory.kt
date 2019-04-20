package com.dvdb.starwars.presentation.film.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dvdb.starwars.common.CompositeDisposableManager
import com.dvdb.starwars.domain.film.detail.FilmDetailUseCases
import io.reactivex.Scheduler

class FilmDetailViewModelFactory(
    private val compositeDisposableManager: CompositeDisposableManager,
    private val filmDetailUseCases: FilmDetailUseCases,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler,
    private val filmTotalRating: Int,
    private val filmTitle: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FilmDetailViewModel(
            compositeDisposableManager,
            filmDetailUseCases,
            subscribeOnScheduler,
            observeOnScheduler,
            filmTotalRating,
            filmTitle
        ) as T
    }
}