package com.tnovoselec.android.pokemon.ui.list

import com.tnovoselec.android.pokemon.base.BaseView
import com.tnovoselec.android.pokemon.base.ScopedPresenter
import com.tnovoselec.android.pokemon.ui.viewmodel.PokemonViewModel


class PokemonListContract {

    interface View : BaseView {

        fun render(pokemonViewModels: List<PokemonViewModel>)
    }

    interface Presenter : ScopedPresenter {

        fun onPokemonSelected(resourceUri: String)
    }
}