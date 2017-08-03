package com.tnovoselec.android.pokemon.ui.router.action

import io.reactivex.functions.Action
import java.util.concurrent.TimeUnit


interface ActionRouter {

    fun setTiming(windowDuration: Long, timeUnit: TimeUnit)

    fun throttle(action: Action)

    fun throttle(windowDuration: Long, timeUnit: TimeUnit, action: Action)

    fun blockActions()

    fun unblockActions()
}
