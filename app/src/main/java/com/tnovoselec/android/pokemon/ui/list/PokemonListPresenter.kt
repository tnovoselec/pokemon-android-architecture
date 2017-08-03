package com.tnovoselec.android.pokemon.ui.list

import com.tnovoselec.android.pokemon.base.BasePresenter
import com.tnovoselec.android.pokemon.domain.interactor.GetPokemonListUseCase
import com.tnovoselec.android.pokemon.ui.list.converter.PokemonListConverter
import com.tnovoselec.android.pokemon.ui.viewmodel.PokemonViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject


class PokemonListPresenter(view: PokemonListContract.View) : BasePresenter<PokemonListContract.View>(view), PokemonListContract.Presenter {

    @Inject lateinit var getPokemonListUseCase: GetPokemonListUseCase
    @Inject lateinit var pokemonListConverter: PokemonListConverter

    override fun start() {
        super.start()
        viewActionQueue.subscribeTo(
                getPokemonListUseCase.execute()
                        .map { pokemonListConverter.toPokemonListViewModels(it)}
                        .map { toViewAction(it) }, Consumer { it.printStackTrace() }
        )

    }

    fun toViewAction(pokemonListViewModels: List<PokemonViewModel>) : Consumer<PokemonListContract.View>{
        return Consumer { it.render(pokemonListViewModels) }
    }

    override fun onPokemonSelected(resourceUri: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}