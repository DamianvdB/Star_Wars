package com.dvdb.starwars.common

import io.reactivex.disposables.CompositeDisposable

interface CompositeDisposableManager {
    val container: CompositeDisposable
}