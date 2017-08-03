package com.tnovoselec.android.pokemon.ui.list.converter

import com.tnovoselec.android.pokemon.domain.model.SimplePokemon
import com.tnovoselec.android.pokemon.ui.viewmodel.PokemonViewModel


class PokemonListConverter {

    fun toPokemonListViewModels(pokemonList: List<SimplePokemon>): List<PokemonViewModel> {
        return pokemonList.map { toPokemonViewModel(it) }
    }

    fun toPokemonViewModel(simplePokemon: SimplePokemon): PokemonViewModel {
        return PokemonViewModel(simplePokemon.name, "", simplePokemon.resource_uri)
    }
}