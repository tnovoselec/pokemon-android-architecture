package com.tnovoselec.android.pokemon.rx.operator

import io.reactivex.FlowableOperator
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit


class OperatorVariableThrottleFirst<T> @JvmOverloads constructor(
        windowDuration: Long,
        unit: TimeUnit,
        private val scheduler: Scheduler = Schedulers.computation()) : FlowableOperator<T, T>, ThrottleController {

    override fun apply(observer: Subscriber<in T>): Subscriber<in T> {
        return object : Subscriber<T> {
            override fun onComplete() {

            }

            override fun onSubscribe(s: Subscription?) {
                s?.request(java.lang.Long.MAX_VALUE)
            }

            private var lastOnNext: Long = 0

            override fun onNext(v: T) {
                val now = scheduler.now(TimeUnit.MILLISECONDS)
                if (lastOnNext == 0L || now - lastOnNext >= timeInMilliseconds) {
                    lastOnNext = now
                    if (newTimeInMilliseconds != 0L) {
                        timeInMilliseconds = newTimeInMilliseconds
                    }
                    observer.onNext(v)
                }
            }

            fun onCompleted() {
                observer.onComplete()
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        }
    }


    private var timeInMilliseconds: Long = 0
    private var newTimeInMilliseconds = 0L

    init {
        this.timeInMilliseconds = unit.toMillis(windowDuration)
    }


    override fun setThrottleWindow(windowDuration: Long, unit: TimeUnit) {
        newTimeInMilliseconds = unit.toMillis(windowDuration)
    }
}
