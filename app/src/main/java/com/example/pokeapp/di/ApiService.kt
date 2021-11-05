package com.example.pokeapp.di

import com.example.pokeapp.data.model.home.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon.json")
    suspend fun getPokemonList() : Call<List<Pokemon>>

}