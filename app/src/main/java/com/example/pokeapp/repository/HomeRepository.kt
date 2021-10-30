package com.example.pokeapp.repository

import com.example.pokeapp.di.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService ){

    suspend fun getPokemonList() =
        apiService.getPokemonList()
}