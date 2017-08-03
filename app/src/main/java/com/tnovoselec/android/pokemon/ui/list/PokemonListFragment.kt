package com.tnovoselec.android.pokemon.ui.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.tnovoselec.android.pokemon.R
import com.tnovoselec.android.pokemon.base.BaseFragment
import com.tnovoselec.android.pokemon.base.ScopedPresenter
import com.tnovoselec.android.pokemon.di.fragment.FragmentComponent
import com.tnovoselec.android.pokemon.ui.viewmodel.PokemonViewModel
import javax.inject.Inject


class PokemonListFragment : BaseFragment(), PokemonListContract.View {

    companion object {
        fun newInstance(): PokemonListFragment {
            return PokemonListFragment()
        }
    }

    @Inject lateinit var pokemonListPresenter: PokemonListContract.Presenter

    @BindView(R.id.pokemon_list_recycler) lateinit var pokemonListRecycler: RecyclerView

    lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        pokemonListAdapter = PokemonListAdapter()
        pokemonListRecycler.layoutManager = LinearLayoutManager(context)
        pokemonListRecycler.adapter = pokemonListAdapter
    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override val presenter: ScopedPresenter
        get() = pokemonListPresenter

    override fun render(pokemonViewModels: List<PokemonViewModel>) {
        pokemonListAdapter.updateData(pokemonViewModels)
    }
}