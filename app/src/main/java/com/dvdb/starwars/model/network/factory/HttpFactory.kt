package com.dvdb.starwars.model.network.factory

import okhttp3.OkHttpClient

internal interface HttpFactory {
    fun getHttpClient(): OkHttpClient
}