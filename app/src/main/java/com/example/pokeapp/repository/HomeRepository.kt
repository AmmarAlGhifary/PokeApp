package com.example.pokeapp.repository

import com.example.pokeapp.di.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getPokemonList() =
        apiService.getPokemonList()

//  suspend fun getPokemonList() = performGetOperation(
//        networkCall = { remoteDataSource.getAllPokemon() },
//        saveCallResult = {localDataSoure.insertAll(it.results)},
//        databaseQuery = {localDataSoure.getAllCharacters()}
//    )

//    fun getCharacter(id: String) = performGetRemoteOnly(
//        networkCall = { remoteDataSource.g(id) }
//    )

//    suspend fun getPokemonById() =
//        apiService.getPokemon(id = 0)
}