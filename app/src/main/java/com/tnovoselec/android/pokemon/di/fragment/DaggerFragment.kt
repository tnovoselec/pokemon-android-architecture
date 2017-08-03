package com.tnovoselec.android.pokemon.di.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.tnovoselec.android.pokemon.di.ComponentFactory
import com.tnovoselec.android.pokemon.di.activity.DaggerActivity


abstract class DaggerFragment : Fragment() {

    private var fragmentComponent: FragmentComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getFragmentComponent())
    }

    protected abstract fun inject(fragmentComponent: FragmentComponent)

    fun getFragmentComponent(): FragmentComponent {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, daggerActivity.getActivityComponent())
        }

        return fragmentComponent!!
    }

    private val daggerActivity: DaggerActivity
        get() = activity as DaggerActivity
}
