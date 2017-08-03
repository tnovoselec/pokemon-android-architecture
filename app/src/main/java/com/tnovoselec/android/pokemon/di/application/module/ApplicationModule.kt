package com.tnovoselec.android.pokemon.di.application.module

import android.content.Context
import android.content.res.Resources
import com.tnovoselec.android.pokemon.configuration.ViewActionQueueProvider
import com.tnovoselec.android.pokemon.configuration.ViewIdGenerator
import com.tnovoselec.android.pokemon.di.application.ForApplication
import com.tnovoselec.android.pokemon.di.application.PokemonApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val pokemonApplication: PokemonApplication) {

    @Provides
    @Singleton
    internal fun providePokemonApplication(): PokemonApplication {
        return pokemonApplication
    }

    @Provides
    @Singleton
    @ForApplication
    internal fun provideContext(): Context {
        return pokemonApplication
    }

    @Provides
    @Singleton
    internal fun provideViewIdGenerator(): ViewIdGenerator {
        return ViewIdGenerator()
    }

    @Provides
    @Singleton
    internal fun provideViewActionQueueProvider(): ViewActionQueueProvider {
        return ViewActionQueueProvider()
    }

    @Provides
    @Singleton
    internal fun provideResources(): Resources {
        return pokemonApplication.getResources()
    }

    interface Exposes {

        fun pokemonApplication(): PokemonApplication

        @ForApplication
        fun context(): Context

        fun viewIdGenerator(): ViewIdGenerator

        fun viewActionQueueProvider(): ViewActionQueueProvider

        fun resources(): Resources
    }
}