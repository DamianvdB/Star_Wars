package com.dvdb.starwars.common

import android.app.Application
import com.dvdb.starwars.domain.FilmListInteractor
import com.dvdb.starwars.domain.FilmListUseCases
import com.dvdb.starwars.model.network.factory.HttpFactory
import com.dvdb.starwars.model.network.factory.HttpFactoryImpl
import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.factory.RetrofitFactoryImpl
import com.dvdb.starwars.model.network.film.FilmApiService
import com.dvdb.starwars.model.network.film.FilmNetworkDataSource
import com.dvdb.starwars.model.network.film.FilmNetworkDataSourceImpl
import com.dvdb.starwars.model.repository.StarWarsRepository
import com.dvdb.starwars.model.repository.StarWarsRepositoryImpl
import com.dvdb.starwars.presentation.splash.SplashScreenViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

private const val KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE = "scheduler_on_subscribe"
private const val KODEIN_TAG_SCHEDULER_ON_OBSERVE = "scheduler_on_observe"

class StarWarsApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@StarWarsApplication))
        bind<HttpFactory>() with provider { HttpFactoryImpl() }
        bind<RetrofitFactory>() with provider { RetrofitFactoryImpl(instance()) }
        bind() from singleton { FilmApiService(instance()) }
        bind<FilmNetworkDataSource>() with singleton { FilmNetworkDataSourceImpl(instance()) }
        bind<StarWarsRepository>() with singleton { StarWarsRepositoryImpl(instance()) }
        bind<CompositeDisposableManager>() with singleton { CompositeDisposableManagerImpl( CompositeDisposable()) }
        bind<FilmListUseCases>() with singleton { FilmListInteractor(instance()) }
        bind(tag = KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE) from singleton { Schedulers.io() }
        bind(tag = KODEIN_TAG_SCHEDULER_ON_OBSERVE) from singleton { AndroidSchedulers.mainThread() }
        bind() from provider { SplashScreenViewModelFactory(instance(), instance(), instance(tag = KODEIN_TAG_SCHEDULER_ON_SUBSCRIBE), instance(tag = KODEIN_TAG_SCHEDULER_ON_OBSERVE)) }
    }
}