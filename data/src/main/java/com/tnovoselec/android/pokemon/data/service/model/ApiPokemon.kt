package com.tnovoselec.android.pokemon.data.service.model


data class ApiPokemon (
        val abilities: List<ApiAbility>,
        val attack: Int,
        val catch_rate: Int,
        val created: String?,
        val defense: Int,
        val egg_cycles: Int,
        val ev_yield: String?,
        val exp: Int,
        val growth_rate: String?,
        val happiness: Int,
        val height: String?,
        val hp: Int,
        val male_female_ratio: String?,
        val modified: String?,
        val name: String?,
        val national_id: Int,
        val resource_uri: String?,
        val sp_atk: Int,
        val sp_def: Int,
        val species: String?,
        val speed: Int,
        val total: Int,
        val types: List<ApiType>,
        val weight: String?)