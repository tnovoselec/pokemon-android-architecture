package com.tnovoselec.android.pokemon.di.application.module

import com.tnovoselec.android.pokemon.domain.interactor.GetPokemonListUseCase
import com.tnovoselec.android.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCaseModule {

    @Provides
    @Singleton
    internal fun provideGetPokemonListUseCase(pokemonRepository: PokemonRepository): GetPokemonListUseCase {
        return GetPokemonListUseCase(pokemonRepository)
    }



    interface Exposes {

        val getPokemonListUseCase: GetPokemonListUseCase

    }
}