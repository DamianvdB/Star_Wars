package com.dvdb.starwars.model.network.film.tmdb

import com.dvdb.starwars.model.network.factory.RetrofitFactory
import com.dvdb.starwars.model.network.film.tmdb.response.TmdbFilmSearchContainerResponse
import io.reactivex.Single
import okhttp3.HttpUrl
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "1e0dcaa7e93980fb84e1d2430d01b887"

internal interface TmdbApiService {

    @GET("search/movie?api_key=$API_KEY")
    fun getFilm(
        @Query("query") title: String,
        @Query("year") yearReleased: Int
    ): Single<TmdbFilmSearchContainerResponse>

    companion object {
        operator fun invoke(
            retrofitFactory: RetrofitFactory
        ): TmdbApiService {
            return retrofitFactory.getRetrofit(HttpUrl.get("https://api.themoviedb.org/3/"))
                .create(TmdbApiService::class.java)
        }
    }
}