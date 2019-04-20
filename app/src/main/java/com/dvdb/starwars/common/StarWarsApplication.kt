package com.dvdb.starwars.common

import android.app.Application
import com.dvdb.starwars.R
import com.dvdb.starwars.common.model.FilmListItem
import com.dvdb.starwars.domain.film.detail.FilmDetailInteractor
import com.dvdb.starwars.domain.film.detail.FilmDetailUseCases
import com.dvdb.starwars.domain.film.list.FilmListInteractor
import com.dvdb.starwars.domain.film.list.FilmListUseCases
import com.dvdb.starwars.model.network.factory.HttpFactory
import com.dvdb.starwars.model.network.factory.HttpFactoryImpl
import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.factory.RetrofitFactoryImpl
import com.dvdb.starwars.model.network.film.swapi.SwapiFilmApiService
import com.dvdb.starwars.model.network.film.swapi.SwapiFilmNetworkDataSource
import com.dvdb.starwars.model.network.film.swapi.SwapiFilmNetworkDataSourceImpl
import com.dvdb.starwars.model.network.film.tmdb.*
import com.dvdb.starwars.model.repository.StarWarsRepository
import com.dvdb.starwars.model.repository.StarWarsRepositoryImpl
import com.dvdb.starwars.presentation.film.detail.FilmDetailViewModelFactory
import com.dvdb.starwars.presentation.film.list.FilmListViewModelFactory
import com.dvdb.starwars.presentation.splash.SplashScreenViewModelFactory
import com.dvdb.starwars.presentation.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

private const val KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE = "scheduler_on_subscribe"
private const val KODEIN_TAG_SCHEDULER_ON_OBSERVE = "scheduler_on_observe"

class StarWarsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@StarWarsApplication))
        bind<NavigationManager.Factory>()  with singleton { NavigationManagerFactoryImpl(this@StarWarsApplication) }
        bind<HttpFactory>() with singleton { HttpFactoryImpl() }
        bind<RetrofitFactory>() with singleton { RetrofitFactoryImpl(instance()) }
        bind() from singleton { SwapiFilmApiService(instance()) }
        bind() from singleton { TmdbApiService(instance()) }
        bind<SwapiFilmNetworkDataSource>() with singleton { SwapiFilmNetworkDataSourceImpl(instance()) }
        bind<TmdbFilmNetworkDataSource>() with singleton { TmdbFilmNetworkDataSourceImpl(instance()) }
        bind<StarWarsRepository>() with singleton { StarWarsRepositoryImpl(instance(), instance(), applicationContext.resources.getInteger(R.integer.retries_on_error).toLong()) }
        bind<CompositeDisposableManager>() with singleton { CompositeDisposableManagerImpl( CompositeDisposable()) }
        bind<TmdbFilmApiPosterPathManager>() with singleton { TmdbFilmApiPosterPathManagerImpl( ) }
        bind() from singleton { FilmListItem.Factory(instance()) }
        bind<FilmListUseCases>() with singleton { FilmListInteractor(instance(), instance()) }
        bind<FilmDetailUseCases>() with singleton {FilmDetailInteractor(instance())}
        bind(tag = KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE) from singleton { Schedulers.io() }
        bind(tag = KODEIN_TAG_SCHEDULER_ON_OBSERVE) from singleton { AndroidSchedulers.mainThread() }
        bind() from provider { SplashScreenViewModelFactory(instance(), instance(), instance(tag = KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE), instance(tag = KODEIN_TAG_SCHEDULER_ON_OBSERVE)) }
        bind() from provider { FilmListViewModelFactory(instance(), instance(), instance(tag = KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE), instance(tag = KODEIN_TAG_SCHEDULER_ON_OBSERVE)) }
        bind() from factory { filmTitle: String -> FilmDetailViewModelFactory(instance(), instance(), instance(tag = KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE), instance(tag = KODEIN_TAG_SCHEDULER_ON_OBSERVE), applicationContext.resources.getInteger(R.integer.film_rating_total),filmTitle) }
        bind<SnackbarFactory>() with singleton { SnackbarFactoryImpl() }
    }
}