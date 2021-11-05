package com.example.pokeapp.di

import com.example.pokeapp.BuildConfig
import com.example.pokeapp.repository.PokemonInterface
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_GIT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create<PokemonInterface>()
    }
}
