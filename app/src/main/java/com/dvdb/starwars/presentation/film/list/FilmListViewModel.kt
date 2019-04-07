package com.dvdb.starwars.presentation.film.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvdb.starwars.common.CompositeDisposableManager
import com.dvdb.starwars.common.model.FilmListItem
import com.dvdb.starwars.domain.film.list.FilmListUseCases
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver

class FilmListViewModel(
    private val compositeDisposableManager: CompositeDisposableManager,
    private val filmListUseCases: FilmListUseCases,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : ViewModel() {
    val stateLiveData = MutableLiveData<FilmListViewModelState>()

    init {
        updateFilmList()
    }

    fun updateFilmList() {
        stateLiveData.value = FilmListViewModelState.Loading(true)
        compositeDisposableManager.container.add(
            filmListUseCases.getFilmListItems()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doFinally { stateLiveData.value = FilmListViewModelState.Loading(false) }
                .subscribeWith(object : DisposableSingleObserver<List<FilmListItem>>() {
                    override fun onSuccess(items: List<FilmListItem>) {
                        stateLiveData.value = FilmListViewModelState.Success(items)
                    }

                    override fun onError(throwable: Throwable) {
                        stateLiveData.value = FilmListViewModelState.Error(throwable)
                    }
                })
        )
    }
}

sealed class FilmListViewModelState {
    data class Loading(val isLoading: Boolean) : FilmListViewModelState()
    data class Success(val listItems: List<FilmListItem>) : FilmListViewModelState()
    data class Error(val throwable: Throwable?) : FilmListViewModelState()
}