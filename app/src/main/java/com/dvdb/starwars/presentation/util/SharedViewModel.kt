package com.dvdb.starwars.presentation.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class SharedViewModel<T> : ViewModel() {
    abstract val data: MutableLiveData<T?>
}