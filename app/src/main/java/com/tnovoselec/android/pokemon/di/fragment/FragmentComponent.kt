package com.tnovoselec.android.pokemon.di.fragment

import com.tnovoselec.android.pokemon.di.activity.ActivityComponent
import com.tnovoselec.android.pokemon.di.fragment.module.FragmentModule
import com.tnovoselec.android.pokemon.di.fragment.module.FragmentPresenterModule
import dagger.Component


@FragmentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(
        FragmentModule::class,
        FragmentPresenterModule::class))
interface FragmentComponent : FragmentComponentInjects, FragmentComponentExposes {

    object Initializer {

        fun init(fragment: DaggerFragment, activityComponent: ActivityComponent): FragmentComponent {
            return DaggerFragmentComponent.builder()
                    .activityComponent(activityComponent)
                    .fragmentModule(FragmentModule(fragment))
                    .fragmentPresenterModule(FragmentPresenterModule(fragment))
                    .build()
        }
    }
}
