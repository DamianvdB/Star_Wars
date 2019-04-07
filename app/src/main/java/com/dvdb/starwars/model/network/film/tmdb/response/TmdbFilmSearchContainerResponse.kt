package com.dvdb.starwars.model.network.film.tmdb.response

import com.google.gson.annotations.SerializedName

data class TmdbFilmSearchContainerResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<TmdbFilmSearchResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)