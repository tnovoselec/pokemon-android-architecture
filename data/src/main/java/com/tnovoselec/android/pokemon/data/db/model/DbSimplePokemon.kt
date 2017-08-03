package com.tnovoselec.android.pokemon.data.db.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "simple_pokemon")
class DbSimplePokemon {

    @ColumnInfo(name = "name")
    var name: String = ""

    @PrimaryKey
    @ColumnInfo(name = "resource_uri")
    var resourceUri: String = ""
}