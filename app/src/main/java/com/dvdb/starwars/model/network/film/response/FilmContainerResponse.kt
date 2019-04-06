package com.dvdb.starwars.model.network.film.response

import com.google.gson.annotations.SerializedName

data class FilmContainerResponse(
    @SerializedName("results") val results: List<FilmReponse>
)