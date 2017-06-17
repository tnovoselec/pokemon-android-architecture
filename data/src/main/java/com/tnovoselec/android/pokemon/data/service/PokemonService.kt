package com.tnovoselec.android.pokemon.data.service

import com.tnovoselec.android.pokemon.data.service.model.ApiPokemon
import com.tnovoselec.android.pokemon.data.service.model.ApiSimplePokemon
import io.reactivex.Single


interface PokemonService {

    fun getPokemons(): Single<List<ApiSimplePokemon>>

    fun getPokemonDetails(resourceUri: String): Single<ApiPokemon>
}