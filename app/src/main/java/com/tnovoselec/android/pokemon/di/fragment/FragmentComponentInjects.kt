package com.tnovoselec.android.pokemon.di.fragment

import com.tnovoselec.android.pokemon.ui.list.PokemonListFragment
import com.tnovoselec.android.pokemon.ui.list.PokemonListPresenter

interface FragmentComponentInjects {

    fun inject(pokemonListFragment: PokemonListFragment)
    fun inject(pokemonListPresenter: PokemonListPresenter)

}
