package com.tnovoselec.android.pokemon.base

import android.os.Bundle
import com.tnovoselec.android.pokemon.configuration.ViewIdGenerator
import com.tnovoselec.android.pokemon.di.fragment.DaggerFragment
import com.tnovoselec.android.pokemon.ui.router.action.ActionRouter
import io.reactivex.functions.Action
import javax.inject.Inject


abstract class BaseFragment : DaggerFragment(), BaseView, BackPropagatingFragment {

    @Inject
    internal lateinit var viewIdGenerator: ViewIdGenerator

    @Inject
    internal lateinit var actionRouter: ActionRouter

    var vId: String = ""
    var recreated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView(savedInstanceState)
        presenter.start()
    }

    private fun initializeView(savedInstanceState: Bundle?) {
        recreated = savedInstanceState != null
        vId = if (savedInstanceState == null)
            viewIdGenerator.newId()
        else
            savedInstanceState.getString(KEY_VIEW_ID)
    }

    override fun onResume() {
        super.onResume()
        presenter.activate()
    }

    override fun onPause() {
        presenter.deactivate()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_VIEW_ID, vId)
    }

    fun onPreDestroy() {
        presenter.destroy()
    }

    override fun onBack(): Boolean {
        actionRouter.throttle(Action { presenter.back() })
        return true
    }

    override fun isRecreated(): Boolean {
        return recreated
    }

    override fun viewId(): String {
        return vId
    }

    abstract val presenter: ScopedPresenter

    companion object {

        private val KEY_VIEW_ID = "view_id"
    }
}
