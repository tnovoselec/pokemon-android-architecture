package com.tnovoselec.android.pokemon.base

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.FragmentManager
import com.tnovoselec.android.pokemon.configuration.ViewIdGenerator
import com.tnovoselec.android.pokemon.di.activity.DaggerActivity
import com.tnovoselec.android.pokemon.ui.router.action.ActionRouter
import com.tnovoselec.android.pokemon.ui.util.ActivityUtils
import javax.inject.Inject


abstract class BaseActivity : DaggerActivity(), BaseView {

    @Inject
    protected lateinit var fragmentManager: FragmentManager

    @Inject
    internal lateinit var viewIdGenerator: ViewIdGenerator

    @Inject
    internal lateinit var actionRouter: ActionRouter

    @Inject
    internal lateinit var activityUtils: ActivityUtils

    var vId: String = ""
    var recreated: Boolean = false

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView(savedInstanceState)
        presenter().start()
    }

    private fun initializeView(@Nullable savedInstanceState: Bundle?) {
        recreated = savedInstanceState != null
        vId = if (savedInstanceState == null)
            viewIdGenerator.newId()
        else
            savedInstanceState.getString(KEY_VIEW_ID)
    }

    override fun onResume() {
        super.onResume()
        presenter().activate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_VIEW_ID, vId)
    }

    override fun onPause() {
        presenter().deactivate()
        super.onPause()
    }

    override fun onBackPressed() {
        if (!activityUtils.propagateBackToTopFragment(fragmentManager)) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (isFinishing) {
            presenter().destroy()
            clearFragments()
        }
        super.onDestroy()
    }

    private fun clearFragments() {
        val fragments = fragmentManager.fragments
        for (fragment in fragments) {
            if (fragment is BaseFragment) {
                fragment.onPreDestroy()
            }
        }
    }

    override fun isRecreated(): Boolean {
        return recreated
    }

    override fun viewId(): String {
        return vId
    }

    protected abstract fun presenter(): ScopedPresenter

    companion object {

        private val KEY_VIEW_ID = "view_id"
    }
}