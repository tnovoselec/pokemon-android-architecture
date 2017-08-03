package com.tnovoselec.android.pokemon.data.service

import com.tnovoselec.android.pokemon.data.service.model.ApiPokemon
import com.tnovoselec.android.pokemon.data.service.model.GetPokemonListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApi {

    @GET("/api/v1/pokedex/1/")
    fun getPokemons(): Single<GetPokemonListResponse>

    @GET("{path}")
    fun getPokemonDetails(@Path("path") resourceUri: String): Single<ApiPokemon>
}