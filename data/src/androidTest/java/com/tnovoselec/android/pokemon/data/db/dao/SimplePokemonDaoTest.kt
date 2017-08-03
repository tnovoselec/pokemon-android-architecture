package com.tnovoselec.android.pokemon.data.db.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.core.deps.guava.collect.ImmutableList
import android.support.test.runner.AndroidJUnit4
import com.tnovoselec.android.pokemon.data.db.PokemonDatabase
import com.tnovoselec.android.pokemon.data.db.model.DbSimplePokemon
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class SimplePokemonDaoTest {

    lateinit var simplePokemonDao: SimplePokemonDao
    lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        pokemonDatabase = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java).build()
        simplePokemonDao = pokemonDatabase.simplePokemonDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadSimplePokemon() {
        val simplePokemon = createSimplePokemon()
        simplePokemonDao.insertPokemons(ImmutableList.of(simplePokemon))
        val listedPokemons = simplePokemonDao.getPokemons()
        assertThat(listedPokemons[0].name, equalTo(simplePokemon.name))
        assertThat(listedPokemons[0].resourceUri, equalTo(simplePokemon.resourceUri))
    }

    fun createSimplePokemon() : DbSimplePokemon{
        val simplePokemon = DbSimplePokemon()
        simplePokemon.name = "Pikachu"
        simplePokemon.resourceUri = "fake_uri"
        return simplePokemon
    }
}