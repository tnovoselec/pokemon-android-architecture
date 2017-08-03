package com.tnovoselec.android.pokemon.ui.router

import android.app.Activity
import android.support.v4.app.FragmentManager
import com.tnovoselec.android.pokemon.di.activity.ActivityScope


@ActivityScope
class RouterImpl(private val activity: Activity, private val fragmentManager: FragmentManager) : Router {

    override fun closeScreen() {
        activity.finish()
    }

    override fun goBack() {
        if (LAST_FRAGMENT === fragmentManager.getBackStackEntryCount()) {
            closeScreen()
        } else {
            fragmentManager.popBackStack()
        }
    }

    companion object {

        private val LAST_FRAGMENT = 0
    }
}
