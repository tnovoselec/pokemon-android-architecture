package com.tnovoselec.android.pokemon.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tnovoselec.android.pokemon.data.db.model.DbSimplePokemon


@Dao
interface SimplePokemonDao {

    @Query("SELECT * from simple_pokemon")
    fun getPokemons(): List<DbSimplePokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemons(pokemons: List<DbSimplePokemon>)
}