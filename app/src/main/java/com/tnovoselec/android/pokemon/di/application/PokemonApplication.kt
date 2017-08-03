package com.tnovoselec.android.pokemon.di.application

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import com.tnovoselec.android.pokemon.di.ComponentFactory

class PokemonApplication : Application() {

   lateinit var applicationComponent: ApplicationComponent
        private set


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        applicationComponent = ComponentFactory.createApplicationComponent(this)
        applicationComponent.inject(this)

        Stetho.initializeWithDefaults(this)
    }
}