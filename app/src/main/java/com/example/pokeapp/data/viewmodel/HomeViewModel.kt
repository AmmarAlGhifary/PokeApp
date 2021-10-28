package com.example.pokeapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.model.pokemonlist.PokemonResponse
import com.example.pokeapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val response : MutableLiveData<PokemonResponse> = MutableLiveData()
    val pokemonResponse : LiveData<PokemonResponse>
        get() = response

    init {
        getPokemon()
    }

    private fun getPokemon() = viewModelScope.launch{
        repository.getPokemonList().let { result ->
            if (result.isSuccessful) {
                response.postValue(result.body())
            } else {
                Log.d("response", "error : ${result.code()}")
            }
        }
    }
}