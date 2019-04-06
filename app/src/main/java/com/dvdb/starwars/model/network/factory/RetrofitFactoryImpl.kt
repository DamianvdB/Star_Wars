package com.dvdb.starwars.model.network.factory

import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitFactoryImpl(
    private val httpFactory: HttpFactory
) : RetrofitFactory {
    override fun getRetrofit(baseUrl: HttpUrl): Retrofit {
       return Retrofit.Builder()
           .baseUrl(baseUrl)
           .client(httpFactory.getHttpClient())
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build()
    }
}