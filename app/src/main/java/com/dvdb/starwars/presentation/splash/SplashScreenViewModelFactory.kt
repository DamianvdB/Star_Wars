package com.dvdb.starwars.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dvdb.starwars.common.CompositeDisposableManager
import com.dvdb.starwars.domain.film.list.FilmListUseCases
import io.reactivex.Scheduler

class SplashScreenViewModelFactory(
    private val compositeDisposableManager: CompositeDisposableManager,
    private val filmListUseCases: FilmListUseCases,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashScreenViewModel(
            compositeDisposableManager,
            filmListUseCases,
            subscribeOnScheduler,
            observeOnScheduler
        ) as T
    }
}