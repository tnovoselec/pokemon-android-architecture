package com.tnovoselec.android.pokemon.di.application.module

import com.tnovoselec.android.pokemon.ui.list.converter.PokemonListConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MappersModule {

    @Provides
    @Singleton
    internal fun providePokemonListConverter(): PokemonListConverter {
        return PokemonListConverter()
    }

    interface Exposes {

        fun pokemonListConverter(): PokemonListConverter
    }
}
