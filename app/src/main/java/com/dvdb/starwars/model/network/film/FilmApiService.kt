package com.dvdb.starwars.model.network.film

import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.film.response.FilmContainerResponse
import io.reactivex.Single
import okhttp3.HttpUrl
import retrofit2.http.GET

internal interface FilmApiService {

    @GET("films")
    fun getFilms(): Single<FilmContainerResponse>

    companion object {
        operator fun invoke(
            retrofitFactory: RetrofitFactory
        ): FilmApiService {
            return retrofitFactory.getRetrofit(HttpUrl.get("https://swapi.co/api/"))
                .create(FilmApiService::class.java)
        }
    }
}