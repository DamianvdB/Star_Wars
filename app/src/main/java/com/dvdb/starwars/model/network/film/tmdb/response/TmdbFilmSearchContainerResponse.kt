package com.dvdb.starwars.model.network.film.tmdb.response

import com.google.gson.annotations.SerializedName

data class TmdbFilmSearchContainerResponse(
    @SerializedName("results") val results: List<TmdbFilmSearchResponse>
)