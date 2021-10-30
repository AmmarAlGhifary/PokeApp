package com.example.pokeapp.data.remote

import com.example.pokeapp.di.ApiService
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun getAllPokemon() = getResult {apiService.getPokemonList()}

}