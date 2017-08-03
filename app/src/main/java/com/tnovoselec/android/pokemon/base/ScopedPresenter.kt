package com.tnovoselec.android.pokemon.base


interface ScopedPresenter {

    fun start()

    fun activate()

    fun deactivate()

    fun destroy()

    fun back()
}