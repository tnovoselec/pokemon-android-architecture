package com.tnovoselec.android.pokemon.configuration

import java.util.*


class ViewIdGenerator {

    fun newId(): String {
        return UUID.randomUUID().toString()
    }
}
