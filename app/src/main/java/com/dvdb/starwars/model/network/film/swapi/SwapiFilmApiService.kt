package com.dvdb.starwars.model.network.film.swapi

import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmContainerResponse
import io.reactivex.Single
import okhttp3.HttpUrl
import retrofit2.http.GET

internal interface SwapiFilmApiService {

    @GET("films")
    fun getFilms(): Single<SwapiFilmContainerResponse>

    companion object {
        operator fun invoke(
            retrofitFactory: RetrofitFactory
        ): SwapiFilmApiService {
            return retrofitFactory.getRetrofit(HttpUrl.get("https://swapi.co/api/"))
                .create(SwapiFilmApiService::class.java)
        }
    }
}