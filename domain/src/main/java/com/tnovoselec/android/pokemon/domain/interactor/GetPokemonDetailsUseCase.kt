package com.tnovoselec.android.pokemon.domain.interactor

import com.tnovoselec.android.pokemon.domain.model.Pokemon
import com.tnovoselec.android.pokemon.domain.repository.PokemonRepository
import io.reactivex.Single


class GetPokemonDetailsUseCase(val pokemonRepository: PokemonRepository) {

    fun getPokemonDetails(resourceUri: String): Single<Pokemon> {
        return Single.defer { pokemonRepository.getPokemonDetails(resourceUri) }
    }

}