package com.example.pokeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokeapp.data.model.home.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAllCharacters(): LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Pokemon>

    @Query("SELECT * FROM pokemon WHERE id IN(:evolutionIds)")
    fun getEvolutionsByIds(evolutionIds: List<String>): LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemon")
    fun all(): LiveData<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(pokemons: List<Pokemon>)

    @Query("DELETE FROM pokemon")
    fun deleteAll()

    @Delete
    fun delete(model: Pokemon)
}