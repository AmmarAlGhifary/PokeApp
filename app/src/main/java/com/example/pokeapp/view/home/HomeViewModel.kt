package com.example.pokeapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.local.PokemonDao
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.repository.PokemonInterface
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.concurrent.thread

class HomeViewModel constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonInterface
) : ViewModel() {

    init {
        getPokemonService()
    }

    fun getListPokemon() = pokemonDao.getAllCharacters()

     private fun getPokemonService() = viewModelScope.launch {
        val call = pokemonService.get()

        call.enqueue(object : Callback<List<Pokemon>?> {
            override fun onResponse(
                call: Call<List<Pokemon>?>?,
                response: Response<List<Pokemon>?>?
            ) {
                response?.body()?.let { pokemons: List<Pokemon> ->
                    thread {
                        pokemonDao.add(pokemons)
                    }
                }
            }

            override fun onFailure(call: Call<List<Pokemon>?>?, t: Throwable?) {
                Timber.d("response", "error")
            }
        })
    }

}

//val response : MutableLiveData<PokemonResponse> = MutableLiveData()
//val pokemonResponse : LiveData<PokemonResponse>
//    get() = response
//
//init {
//    getPokemon()
//}
//
//private fun getPokemon() = viewModelScope.launch{
//    repository.getPokemonList().let { result ->
//        if (result.isSuccessful) {
//            response.postValue(result.body())
//        } else {
//            Timber.d("response", "error : ${result.code()}")
//        }
//    }
//}