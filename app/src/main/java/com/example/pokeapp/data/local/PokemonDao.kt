package com.example.pokeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokeapp.data.model.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAllCharacters() : LiveData<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Pokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<Pokemon>)
}