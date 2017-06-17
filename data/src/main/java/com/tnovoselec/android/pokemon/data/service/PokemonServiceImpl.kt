package com.tnovoselec.android.pokemon.data.service

import com.tnovoselec.android.pokemon.data.service.model.ApiPokemon
import com.tnovoselec.android.pokemon.data.service.model.ApiSimplePokemon
import io.reactivex.Single


class PokemonServiceImpl(val pokemonApi: PokemonApi) : PokemonService {

    override fun getPokemons(): Single<List<ApiSimplePokemon>> {
      return pokemonApi.getPokemons()
    }

    override fun getPokemonDetails(resourceUri: String): Single<ApiPokemon> {
       return pokemonApi.getPokemonDetails(resourceUri)
    }
}