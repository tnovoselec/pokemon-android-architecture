package com.tnovoselec.android.pokemon.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tnovoselec.android.pokemon.data.db.dao.SimplePokemonDao
import com.tnovoselec.android.pokemon.data.db.model.DbSimplePokemon

@Database(entities = arrayOf(DbSimplePokemon::class), version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    companion object {
        val NAME = "PokemonDb"
    }

    abstract fun simplePokemonDao(): SimplePokemonDao
}