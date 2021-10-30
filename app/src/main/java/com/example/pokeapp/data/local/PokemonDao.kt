package com.example.pokeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapp.data.model.pokemonlist.list.Character

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAllCharacters() : LiveData<List<Character>>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)
}