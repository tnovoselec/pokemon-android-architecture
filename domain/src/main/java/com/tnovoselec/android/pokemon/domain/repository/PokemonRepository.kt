package com.tnovoselec.android.pokemon.domain.repository

import com.tnovoselec.android.pokemon.domain.model.Pokemon
import com.tnovoselec.android.pokemon.domain.model.SimplePokemon
import io.reactivex.Single


interface PokemonRepository {

    fun getPokemons(): Single<List<SimplePokemon>>

    fun getPokemonDetails(resourceUri: String): Single<Pokemon>
}