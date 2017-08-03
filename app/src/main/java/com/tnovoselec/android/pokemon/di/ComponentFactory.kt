package com.tnovoselec.android.pokemon.di

import com.tnovoselec.android.pokemon.di.activity.ActivityComponent
import com.tnovoselec.android.pokemon.di.activity.DaggerActivity
import com.tnovoselec.android.pokemon.di.application.ApplicationComponent
import com.tnovoselec.android.pokemon.di.application.PokemonApplication
import com.tnovoselec.android.pokemon.di.fragment.DaggerFragment
import com.tnovoselec.android.pokemon.di.fragment.FragmentComponent


object ComponentFactory {

    fun createApplicationComponent(pokemonApplication: PokemonApplication): ApplicationComponent {
        return ApplicationComponent.Initializer.init(pokemonApplication)
    }

    fun createActivityComponent(daggerActivity: DaggerActivity, pokemonApplication: PokemonApplication): ActivityComponent {
        return ActivityComponent.Initializer.init(daggerActivity, pokemonApplication.applicationComponent)
    }

    fun createFragmentComponent(daggerFragment: DaggerFragment, activityComponent: ActivityComponent): FragmentComponent {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent)
    }
}