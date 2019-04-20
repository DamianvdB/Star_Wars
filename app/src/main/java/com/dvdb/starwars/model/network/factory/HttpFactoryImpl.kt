package com.dvdb.starwars.model.network.factory

import okhttp3.OkHttpClient

internal class HttpFactoryImpl : HttpFactory {

    override fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}