package com.tnovoselec.android.pokemon.di.activity

import com.tnovoselec.android.pokemon.PokemonActivity
import com.tnovoselec.android.pokemon.PokemonPresenter


interface ActivityComponentInjects {

    fun inject(pokemonActivity: PokemonActivity)
    fun inject(pokemonPresenter: PokemonPresenter)
}
