package com.tnovoselec.android.pokemon.data

import com.tnovoselec.android.pokemon.data.converter.DbDomainConverter
import com.tnovoselec.android.pokemon.data.db.dao.SimplePokemonDao
import com.tnovoselec.android.pokemon.data.service.PokemonService
import com.tnovoselec.android.pokemon.domain.model.Pokemon
import com.tnovoselec.android.pokemon.domain.model.SimplePokemon
import com.tnovoselec.android.pokemon.domain.repository.PokemonRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable


class PokemonRepositoryImpl(val pokemonService: PokemonService,
                            val simplePokemonDao: SimplePokemonDao,
                            val dbDomainConverter: DbDomainConverter) : PokemonRepository {

    override fun getPokemonDetails(resourceUri: String): Single<Pokemon> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPokemons(): Single<List<SimplePokemon>> {
//        return Single.defer ( ()-> pokemonService.getPokemons().doOnSuccess { persistPokemons(it) })
        return Single.defer ( Callable { pokemonService.getPokemons().doOnSuccess { persistPokemons(it) } })

    }

    override fun persistPokemons(simplePokemons: List<SimplePokemon>) {
        simplePokemonDao.insertPokemons(
                simplePokemons.map { dbDomainConverter.toDbSimplePokemon(it) })
    }
}