package com.tnovoselec.android.pokemon.ui.router.action

import com.tnovoselec.android.pokemon.rx.operator.OperatorVariableThrottleFirst
import com.tnovoselec.android.pokemon.rx.operator.ThrottleController
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.processors.PublishProcessor
import java.util.concurrent.TimeUnit


class ActionRouterImpl : ActionRouter {

    private var router: PublishProcessor<Action>? = null
    private var routerSubscription: Disposable? = null
    private var actionsBlocked: Boolean = false

    private var primaryWindowDuration = DEFAULT_WINDOW_DURATION_MILLIS
    private var primaryTimeUnit = TimeUnit.MILLISECONDS

    private var throttleController: ThrottleController? = null

    init {
        initRouter()
        listenRouter()
    }

    override fun setTiming(windowDuration: Long, timeUnit: TimeUnit) {
        unSubscribe()
        primaryWindowDuration = windowDuration
        primaryTimeUnit = timeUnit
        listenRouter()
    }

    override fun throttle(windowDuration: Long, timeUnit: TimeUnit, action: Action) {
        if (router != null && throttleController != null && !actionsBlocked) {
            try {
                throttleController!!.setThrottleWindow(windowDuration, timeUnit)
                router!!.onNext(action)
            } catch (throwable: Throwable) {
                System.err.println(throwable.message)
                unSubscribe()
                initRouter()
                listenRouter()
                throw throwable
            }

        }
    }

    override fun blockActions() {
        println("Blocking actions")
        actionsBlocked = true
    }

    override fun unblockActions() {
        println("Unblocking actions")
        actionsBlocked = false
    }

    override fun throttle(action: Action) {
        throttle(primaryWindowDuration, primaryTimeUnit, action)
    }

    private fun initRouter() {
        router = PublishProcessor.create()
    }

    private fun listenRouter() {
        if (router != null) {
            val operatorVariableThrottleFirst = OperatorVariableThrottleFirst<Action>(primaryWindowDuration, primaryTimeUnit)
            routerSubscription = router!!.lift(operatorVariableThrottleFirst)
                    .subscribe { it.run() }
            throttleController = operatorVariableThrottleFirst
        }
    }

    private fun unSubscribe() {
        if (routerSubscription != null && !routerSubscription!!.isDisposed) {
            routerSubscription!!.dispose()
            routerSubscription = null
        }
    }

    companion object {

        private val DEFAULT_WINDOW_DURATION_MILLIS = 500L
    }
}