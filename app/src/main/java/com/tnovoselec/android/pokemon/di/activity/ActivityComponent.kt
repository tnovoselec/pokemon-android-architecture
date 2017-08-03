package com.tnovoselec.android.pokemon.di.activity

import com.tnovoselec.android.pokemon.di.activity.module.ActivityModule
import com.tnovoselec.android.pokemon.di.application.ApplicationComponent
import dagger.Component
import java.util.*

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent : ActivityComponentInjects, ActivityComponentExposes {

    object Initializer {

        fun init(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent): ActivityComponent {
            return DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityModule(ActivityModule(daggerActivity))
                    .build()
        }
    }
}
