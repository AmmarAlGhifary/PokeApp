package com.example.pokeapp.repository

import com.example.pokeapp.data.model.home.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokemonInterface {

    @GET("pokemon.json")
    fun get() : Call<List<Pokemon>>
}