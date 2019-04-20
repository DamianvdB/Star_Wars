package com.dvdb.starwars.model.network.factory

import okhttp3.HttpUrl
import retrofit2.Retrofit

internal interface RetrofitFactory {
    fun getRetrofit(
        baseUrl: HttpUrl
    ): Retrofit
}