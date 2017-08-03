package com.tnovoselec.android.pokemon.data.service

import com.tnovoselec.android.pokemon.domain.model.Pokemon
import com.tnovoselec.android.pokemon.domain.model.SimplePokemon
import io.reactivex.Single


interface PokemonService {

    fun getPokemons(): Single<List<SimplePokemon>>

    fun getPokemonDetails(resourceUri: String): Single<Pokemon>
}