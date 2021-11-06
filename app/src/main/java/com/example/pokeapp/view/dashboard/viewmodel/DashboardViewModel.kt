package com.example.pokeapp.view.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.data.local.PokemonDao
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.view.stat.StatsFragment

class DashboardViewModel(private val pokemonDAO: PokemonDao) : ViewModel() {

    fun getPokemonById(id: String) : LiveData<Pokemon> {
        return pokemonDAO.getCharacter(id)
    }

    fun getEvolutionByIds(ids: List<String>): LiveData<List<Pokemon>> {
        return pokemonDAO.getEvolutionsByIds(ids)
    }
}