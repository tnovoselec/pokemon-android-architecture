package com.tnovoselec.android.pokemon.domain.interactor

import com.tnovoselec.android.pokemon.domain.model.SimplePokemon
import com.tnovoselec.android.pokemon.domain.repository.PokemonRepository
import io.reactivex.Single


class GetPokemonListUseCase(val pokemonRepository: PokemonRepository) {

    fun execute(): Single<List<SimplePokemon>> {
        return Single.defer( { pokemonRepository.getPokemons() })
    }
}