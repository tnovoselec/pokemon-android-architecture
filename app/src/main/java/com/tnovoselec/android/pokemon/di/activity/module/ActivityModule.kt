package com.tnovoselec.android.pokemon.di.activity.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import com.tnovoselec.android.pokemon.PokemonActivity
import com.tnovoselec.android.pokemon.PokemonPresenter
import com.tnovoselec.android.pokemon.di.activity.ActivityScope
import com.tnovoselec.android.pokemon.di.activity.DaggerActivity
import com.tnovoselec.android.pokemon.di.activity.ForActivity
import com.tnovoselec.android.pokemon.ui.router.Router
import com.tnovoselec.android.pokemon.ui.router.RouterImpl
import com.tnovoselec.android.pokemon.ui.router.action.ActionRouter
import com.tnovoselec.android.pokemon.ui.router.action.ActionRouterImpl
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val daggerActivity: DaggerActivity) {

    @Provides
    @ActivityScope
    @ForActivity
    internal fun provideActivityContext(): Context {
        return daggerActivity
    }

    @Provides
    @ActivityScope
    internal fun provideActivity(): Activity {
        return daggerActivity
    }

    @Provides
    @ActivityScope
    internal fun provideFragmentManager(): FragmentManager {
        return daggerActivity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    internal fun provideRouter(fragmentManager: FragmentManager): Router {
        return RouterImpl(daggerActivity, fragmentManager)
    }

    @Provides
    @ActivityScope
    internal fun provideActionRouter(): ActionRouter {
        return ActionRouterImpl()
    }

    @Provides
    @ActivityScope
    internal fun providePokemonPresenter(): PokemonPresenter {
        val pokemonPresenter = PokemonPresenter(daggerActivity as PokemonActivity)
        daggerActivity.getActivityComponent().inject(pokemonPresenter)
        return pokemonPresenter
    }

    interface Exposes {

        fun activity(): Activity

        @ForActivity
        fun context(): Context

        fun fragmentManager(): FragmentManager

        fun router(): Router

        fun actionRouter(): ActionRouter
    }
}
