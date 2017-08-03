package com.tnovoselec.android.pokemon.data.service

import com.tnovoselec.android.pokemon.data.service.model.ApiPokemon
import com.tnovoselec.android.pokemon.data.service.model.ApiSimplePokemon
import com.tnovoselec.android.pokemon.domain.model.Ability
import com.tnovoselec.android.pokemon.domain.model.Pokemon
import com.tnovoselec.android.pokemon.domain.model.SimplePokemon
import com.tnovoselec.android.pokemon.domain.model.Type
import io.reactivex.Single


class PokemonServiceImpl(val pokemonApi: PokemonApi) : PokemonService {

    override fun getPokemons(): Single<List<SimplePokemon>> {
        return pokemonApi.getPokemons().map { it.pokemon }.map { toSimplePokemons(it) }
    }

    override fun getPokemonDetails(resourceUri: String): Single<Pokemon> {
        return pokemonApi.getPokemonDetails(resourceUri).map { toPokemon(it) }
    }

    fun toSimplePokemons(apiSimplePokemons: List<ApiSimplePokemon>): List<SimplePokemon> {
        return apiSimplePokemons.map { SimplePokemon(it.name, it.resource_uri) }
    }

    fun toPokemon(apiPokemon: ApiPokemon): Pokemon {
        return Pokemon(
                apiPokemon.abilities.map { Ability(it.name, it.resource_uri) },
                apiPokemon.attack,
                apiPokemon.catch_rate,
                apiPokemon.created,
                apiPokemon.defense,
                apiPokemon.egg_cycles,
                apiPokemon.ev_yield,
                apiPokemon.exp,
                apiPokemon.growth_rate,
                apiPokemon.happiness,
                apiPokemon.height,
                apiPokemon.hp,
                apiPokemon.male_female_ratio,
                apiPokemon.modified,
                apiPokemon.name,
                apiPokemon.national_id,
                apiPokemon.resource_uri,
                apiPokemon.sp_atk,
                apiPokemon.sp_def,
                apiPokemon.species,
                apiPokemon.speed,
                apiPokemon.total,
                apiPokemon.types.map { Type(it.name, it.resource_uri) },
                apiPokemon.weight
        )
    }
}