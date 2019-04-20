package com.dvdb.starwars.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvdb.starwars.common.CompositeDisposableManager
import com.dvdb.starwars.common.model.FilmListItem
import com.dvdb.starwars.domain.film.list.FilmListUseCases
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver

class SplashScreenViewModel(
    private val compositeDisposableManager: CompositeDisposableManager,
    private val filmListUseCases: FilmListUseCases,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : ViewModel() {
    val stateLiveData = MutableLiveData<SplashScreenViewModelState>()

    init {
        updateFilmItemList()
    }

    fun updateFilmItemList() {
        stateLiveData.value = SplashScreenViewModelState.Loading(true)
        compositeDisposableManager.container.add(
            filmListUseCases.getFilmListItems()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doFinally{stateLiveData.value = SplashScreenViewModelState.Loading(false)}
                .subscribeWith(object : DisposableSingleObserver<List<FilmListItem>>() {
                    override fun onSuccess(items: List<FilmListItem>) {
                        stateLiveData.value = SplashScreenViewModelState.Success(items)
                    }

                    override fun onError(throwable: Throwable) {
                        stateLiveData.value = SplashScreenViewModelState.Error(throwable)
                    }
                })
        )
    }
}

sealed class SplashScreenViewModelState {
    data class Loading(val isLoading: Boolean) : SplashScreenViewModelState()
    data class Success(val listItems: List<FilmListItem>) : SplashScreenViewModelState()
    data class Error(val throwable: Throwable?) : SplashScreenViewModelState()
}
