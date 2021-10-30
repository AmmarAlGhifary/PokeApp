package com.example.pokeapp.data.model.pokemonlist.list

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pokemon")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val url: String
) :Serializable