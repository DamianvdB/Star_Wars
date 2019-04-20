package com.dvdb.starwars.model.network.film.swapi

import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmContainerResponse
import com.dvdb.starwars.model.network.film.swapi.response.SwapiFilmPersonResponse
import io.reactivex.Single
import okhttp3.HttpUrl
import retrofit2.http.GET
import retrofit2.http.Path

internal interface SwapiFilmApiService {

    @GET("films")
    fun getFilms(): Single<SwapiFilmContainerResponse>

    @GET("people/{personIndex}")
    fun getPerson(
        @Path("personIndex") personIndex: Int
    ): Single<SwapiFilmPersonResponse>

    companion object {
        operator fun invoke(
            retrofitFactory: RetrofitFactory
        ): SwapiFilmApiService {
            return retrofitFactory.getRetrofit(HttpUrl.get("https://swapi.co/api/"))
                .create(SwapiFilmApiService::class.java)
        }
    }
}