package com.dvdb.starwars.common.model

import android.net.Uri

class FilmListItem(
    val title: String,
    val releaseDate: String,
    val director: String,
    val producer: String,
    val filmUrl: Uri
)
