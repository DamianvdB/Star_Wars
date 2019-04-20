package com.dvdb.starwars.presentation.film.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dvdb.starwars.common.CompositeDisposableManager
import com.dvdb.starwars.common.model.FilmDetail
import com.dvdb.starwars.domain.film.detail.FilmDetailUseCases
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver

class FilmDetailViewModel(
    private val compositeDisposableManager: CompositeDisposableManager,
    private val filmDetailUseCases: FilmDetailUseCases,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler,
    val filmTotalRating: Int,
    val filmTitle: String
) : ViewModel() {
    val stateLiveData = MutableLiveData<FilmDetailViewModelState>()

    fun updateFilmDetail() {
        stateLiveData.value = FilmDetailViewModelState.Loading(true)
        compositeDisposableManager.container.add(
            filmDetailUseCases.getFilmDetail(filmTitle)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doFinally { stateLiveData.value = FilmDetailViewModelState.Loading(false) }
                .subscribeWith(object : DisposableSingleObserver<FilmDetail>() {
                    override fun onSuccess(filmDetail: FilmDetail) {
                        stateLiveData.value = FilmDetailViewModelState.Success(filmDetail)
                    }

                    override fun onError(throwable: Throwable) {
                        stateLiveData.value = FilmDetailViewModelState.Error(throwable)
                    }
                })
        )
    }
}

sealed class FilmDetailViewModelState {
    data class Loading(val isLoading: Boolean) : FilmDetailViewModelState()
    data class Success(val filmDetail: FilmDetail) : FilmDetailViewModelState()
    data class Error(val throwable: Throwable?) : FilmDetailViewModelState()
}