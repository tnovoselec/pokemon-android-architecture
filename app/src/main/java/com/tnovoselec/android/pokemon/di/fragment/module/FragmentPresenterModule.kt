package com.tnovoselec.android.pokemon.di.fragment.module

import com.tnovoselec.android.pokemon.di.fragment.DaggerFragment
import com.tnovoselec.android.pokemon.di.fragment.FragmentComponent
import com.tnovoselec.android.pokemon.ui.list.PokemonListContract
import com.tnovoselec.android.pokemon.ui.list.PokemonListPresenter
import dagger.Module
import dagger.Provides


@Module
class FragmentPresenterModule(private val daggerFragment: DaggerFragment) {

    private val fragmentComponent: FragmentComponent
        get() = daggerFragment.getFragmentComponent()

    @Provides
    fun providePokemonListPresenter(): PokemonListContract.Presenter {
        val pokemonListPresenter = PokemonListPresenter(daggerFragment as PokemonListContract.View)
        fragmentComponent.inject(pokemonListPresenter)
        return pokemonListPresenter
    }

}
