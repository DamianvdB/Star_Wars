package com.dvdb.starwars.presentation.film.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dvdb.starwars.common.CompositeDisposableManager
import com.dvdb.starwars.domain.FilmListUseCases
import io.reactivex.Scheduler

class FilmListViewModelFactory(
    private val compositeDisposableManager: CompositeDisposableManager,
    private val filmListUseCases: FilmListUseCases,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FilmListViewModel(
            compositeDisposableManager,
            filmListUseCases,
            subscribeOnScheduler,
            observeOnScheduler
        ) as T
    }
}