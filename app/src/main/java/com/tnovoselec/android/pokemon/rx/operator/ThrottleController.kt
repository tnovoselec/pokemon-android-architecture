package com.tnovoselec.android.pokemon.rx.operator

import java.util.concurrent.TimeUnit


interface ThrottleController {

    fun setThrottleWindow(windowDuration: Long, unit: TimeUnit)
}
