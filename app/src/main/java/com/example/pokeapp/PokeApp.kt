package com.example.pokeapp

import android.app.Application
import com.example.pokeapp.di.appComponent
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin{
        androidContext(this@PokeApp)
        modules(appComponent)
    }
}