package com.dvdb.starwars.common

import android.app.Application
import com.dvdb.starwars.domain.FilmListInteractor
import com.dvdb.starwars.domain.FilmListUseCases
import com.dvdb.starwars.model.network.factory.HttpFactoryImpl
import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.factory.RetrofitFactoryImpl
import com.dvdb.starwars.model.network.film.FilmApiService
import com.dvdb.starwars.model.network.film.FilmNetworkDataSource
import com.dvdb.starwars.model.network.film.FilmNetworkDataSourceImpl
import com.dvdb.starwars.model.network.factory.HttpFactory
import com.dvdb.starwars.model.repository.StarWarsRepository
import com.dvdb.starwars.model.repository.StarWarsRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class StarWarsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@StarWarsApplication))

        bind<HttpFactory>() with provider { HttpFactoryImpl() }
        bind<RetrofitFactory>() with provider { RetrofitFactoryImpl(instance()) }
        bind() from singleton { FilmApiService(instance()) }
        bind<FilmNetworkDataSource>() with singleton { FilmNetworkDataSourceImpl(instance()) }
        bind<StarWarsRepository>() with singleton { StarWarsRepositoryImpl(instance()) }
        bind<FilmListUseCases>() with singleton { FilmListInteractor(instance()) }
    }
}