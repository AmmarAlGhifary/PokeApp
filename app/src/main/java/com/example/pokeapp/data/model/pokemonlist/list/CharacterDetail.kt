package com.example.pokeapp.data.model.pokemonlist.list

import java.io.Serializable

data class CharacterDetail(
    val base_stat: String,
    val stat: Stat
) : Serializable

data class Stat(
    val name: String
) : Serializable
