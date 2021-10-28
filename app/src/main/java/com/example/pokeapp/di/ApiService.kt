package com.example.pokeapp.di

import com.example.pokeapp.data.model.pokemonlist.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemon() : Response<PokemonResponse>
}