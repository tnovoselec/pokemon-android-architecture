package com.tnovoselec.android.pokemon.base

import android.support.annotation.CallSuper
import android.support.annotation.Nullable
import android.text.TextUtils
import android.util.Log
import com.tnovoselec.android.pokemon.configuration.ViewActionQueue
import com.tnovoselec.android.pokemon.configuration.ViewActionQueueProvider
import com.tnovoselec.android.pokemon.data.util.connectivity.ConnectivityReceiver
import com.tnovoselec.android.pokemon.di.application.module.ThreadingModule
import com.tnovoselec.android.pokemon.ui.router.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Named


abstract class BasePresenter<View : BaseView>(view: View) : ScopedPresenter {

    @Inject
    protected lateinit var viewActionQueueProvider: ViewActionQueueProvider

    @Inject
    protected lateinit var connectivityReceiver: ConnectivityReceiver

    @Inject
    protected lateinit var router: Router

    @field:[Inject Named(ThreadingModule.MAIN_SCHEDULER)]
    protected lateinit var mainThreadScheduler: Scheduler

    private var viewReference = WeakReference<View>(null)
    private var viewActionsSubscription: Disposable? = null

    protected lateinit var viewId: String
    protected lateinit var viewActionQueue: ViewActionQueue<View>

    private var subscriptions: CompositeDisposable? = CompositeDisposable()

    init {
        viewReference = WeakReference(view)
    }

    @CallSuper
    override fun start() {
        viewId = getIfViewNotNull(Function { it.viewId() }, "")
        viewActionQueue = viewActionQueueProvider.queueFor(viewId) as ViewActionQueue<View>
        subscribeToConnectivityChange()
    }

    private fun subscribeToConnectivityChange() {
        addSubscription(connectivityReceiver.connectivityStatus
                .observeOn(mainThreadScheduler)
                .subscribeOn(Schedulers.io())
                .subscribe({ this.onConnectivityChange(it) }, { this.logError(it) }))
    }

    protected fun onConnectivityChange(isConnected: Boolean) {
        // Template method
    }

    @CallSuper
    override fun activate() {
        viewActionsSubscription = viewActionQueue.viewActionsObservable()
                .observeOn(mainThreadScheduler)
                .subscribe({ this.onViewAction(it) })
        viewActionQueue.resume()
    }

    protected fun onViewAction(viewAction: Consumer<View>) {
        doIfViewNotNull(Consumer<View> { viewAction.accept(it) })
    }

    @CallSuper
    override fun deactivate() {
        viewActionQueue.pause()
        viewActionsSubscription?.dispose()
        subscriptions?.clear()
    }

    @CallSuper
    override fun destroy() {
        viewActionQueue.destroy()
        viewActionQueueProvider.dispose(viewId)
    }

    override fun back() {
        router.goBack()
    }

    protected fun addSubscription(disposable: Disposable) {
        if (subscriptions == null || subscriptions?.isDisposed!!) {
            subscriptions = CompositeDisposable()
        }
        subscriptions?.add(disposable)
    }

    protected fun doIfConnectedToInternet(ifConnected: Action, ifNotConnected: Action) {
        addSubscription(connectivityReceiver.isConnected()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThreadScheduler)
                .subscribe({ isConnected -> onConnectedToInternet(isConnected, ifConnected, ifNotConnected) }, { this.logError(it) }))
    }

    private fun onConnectedToInternet(isConnected: Boolean, ifConnected: Action, ifNotConnected: Action) {
        (if (isConnected) ifConnected else ifNotConnected).run()
    }

    protected fun logError(throwable: Throwable) {
        if (!TextUtils.isEmpty(throwable.message)) {
            Log.e(javaClass.simpleName, throwable.message, throwable)
        }
        // TODO - crashlytics
    }

    protected fun doIfViewNotNull(whenViewNotNull: Consumer<View>) {
        val view = nullableView
        if (view != null) {
            whenViewNotNull.accept(view)
        }
    }

    protected fun <R> getIfViewNotNull(whenViewNotNull: Function<View, R>, defaultValue: R): R {
        val view = nullableView
        if (view != null) {
            return whenViewNotNull.apply(view)
        }
        return defaultValue
    }

    protected val nullableView: View?
        @Nullable
        get() = viewReference.get()
}
