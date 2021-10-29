package com.example.pokeapp.data.model.pokemonlist.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mypokemon")
data class MyCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val rename: String,
    val url: String,
    val current: Int,
    val last: Int
)