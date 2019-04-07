package com.dvdb.starwars.common.model

data class FilmDetail(
    val title: String,
    val releaseDate: String,
    val ratingOutOf5: Int,
    val characters: List<String>,
    val openingCrawl: String
)