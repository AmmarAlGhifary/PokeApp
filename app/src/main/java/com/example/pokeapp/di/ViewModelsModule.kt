package com.example.pokeapp.di

import com.example.pokeapp.view.dashboard.viewmodel.DashboardViewModel
import com.example.pokeapp.view.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
}
