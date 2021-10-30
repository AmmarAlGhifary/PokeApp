package com.example.pokeapp.di

import com.example.pokeapp.data.model.pokemonlist.list.CharacterDetailList
import com.example.pokeapp.data.model.pokemonlist.list.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList() : Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") id : String) : Response<CharacterDetailList>
}