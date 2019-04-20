package com.dvdb.starwars.presentation.film.list

import androidx.lifecycle.MutableLiveData
import com.dvdb.starwars.presentation.util.SharedViewModel

class FilmListSharedViewModel : SharedViewModel<String>() {
    override val data: MutableLiveData<String?> = MutableLiveData()
}