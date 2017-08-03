package com.tnovoselec.android.pokemon.di.activity

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import com.tnovoselec.android.pokemon.di.ComponentFactory
import com.tnovoselec.android.pokemon.di.application.PokemonApplication


abstract class DaggerActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getActivityComponent())
    }

    fun getActivityComponent(): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, pokemonApplication)
        }
        return activityComponent!!
    }

    private val pokemonApplication: PokemonApplication
        get() = application as PokemonApplication

    protected abstract fun inject(activityComponent: ActivityComponent)
}
