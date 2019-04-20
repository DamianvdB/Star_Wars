package com.dvdb.starwars.presentation.util

import android.view.View

internal interface ListItemClickListener<T> {
    fun onItemClicked(view: View, item: T)
}