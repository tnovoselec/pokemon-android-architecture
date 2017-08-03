package com.tnovoselec.android.pokemon

import android.os.Bundle
import com.tnovoselec.android.pokemon.base.BaseActivity
import com.tnovoselec.android.pokemon.base.ScopedPresenter
import com.tnovoselec.android.pokemon.di.activity.ActivityComponent
import com.tnovoselec.android.pokemon.ui.list.PokemonListFragment
import javax.inject.Inject


class PokemonActivity : BaseActivity() {


    @Inject
    lateinit var presenter: PokemonPresenter

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun presenter(): ScopedPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        if (savedInstanceState == null) {
            activityUtils.addFragmentToActivity(fragmentManager, PokemonListFragment.newInstance(), R.id.activity_container)
        }
    }

}