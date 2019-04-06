package com.dvdb.starwars.common

import io.reactivex.disposables.CompositeDisposable

class CompositeDisposableManagerImpl(
    override val container: CompositeDisposable
) : CompositeDisposableManager