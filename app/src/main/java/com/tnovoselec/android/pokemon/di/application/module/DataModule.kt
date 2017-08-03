package com.tnovoselec.android.pokemon.di.application.module

import android.arch.persistence.room.Room
import android.content.Context
import com.tnovoselec.android.pokemon.data.PokemonRepositoryImpl
import com.tnovoselec.android.pokemon.data.converter.DbDomainConverter
import com.tnovoselec.android.pokemon.data.db.PokemonDatabase
import com.tnovoselec.android.pokemon.data.db.dao.SimplePokemonDao
import com.tnovoselec.android.pokemon.data.service.PokemonService
import com.tnovoselec.android.pokemon.di.application.ForApplication
import com.tnovoselec.android.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    internal fun providePokemonRepository(pokemonService: PokemonService, simplePokemonDao: SimplePokemonDao, dbDomainConverter: DbDomainConverter): PokemonRepository {
        return PokemonRepositoryImpl(pokemonService, simplePokemonDao, dbDomainConverter)
    }

    @Provides
    @Singleton
    internal fun provideDbDomainConverter(): DbDomainConverter {
        return DbDomainConverter()
    }

    @Provides
    @Singleton
    internal fun providePokemonDatabase(@ForApplication context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, PokemonDatabase.NAME).build()
    }

    @Provides
    @Singleton
    internal fun provideSimplePokemonDao(pokemonDatabase: PokemonDatabase): SimplePokemonDao {
        return pokemonDatabase.simplePokemonDao()
    }

    interface Exposes {

        fun pokemonRepository(): PokemonRepository

        fun dbDomainConverter(): DbDomainConverter
    }
}