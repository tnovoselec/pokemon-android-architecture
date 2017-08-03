package com.tnovoselec.android.pokemon.data.converter

import com.tnovoselec.android.pokemon.data.db.model.DbSimplePokemon
import com.tnovoselec.android.pokemon.domain.model.SimplePokemon


class DbDomainConverter {

    fun toDbSimplePokemon(simplePokemon: SimplePokemon): DbSimplePokemon {
        val dbSimplePokemon = DbSimplePokemon()
        dbSimplePokemon.resourceUri = simplePokemon.resource_uri
        dbSimplePokemon.name = simplePokemon.name
        return dbSimplePokemon
    }
}