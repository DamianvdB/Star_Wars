package com.dvdb.starwars.model.network.film.swapi.response

import com.google.gson.annotations.SerializedName

data class SwapiFilmContainerResponse(
    @SerializedName("results") val results: List<SwapiFilmResponse>
)