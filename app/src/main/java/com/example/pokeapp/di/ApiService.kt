package com.example.pokeapp.di

import com.example.pokeapp.data.model.pokemondetail.CharacterResponse
import com.example.pokeapp.data.model.pokemonlist.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemon() : Response<PokemonResponse>
}