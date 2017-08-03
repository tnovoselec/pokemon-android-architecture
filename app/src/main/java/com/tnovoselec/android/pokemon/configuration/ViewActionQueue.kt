package com.tnovoselec.android.pokemon.configuration

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import java.util.*


class ViewActionQueue<View> {

    private val viewActions = LinkedList<Consumer<View>>()
    private val queueLock = Any()

    private val viewActionSubject: PublishProcessor<Consumer<View>> = PublishProcessor.create()
    private val subscriptions = CompositeDisposable()

    private var isPaused: Boolean = false

    fun subscribeTo(flowable: Flowable<Consumer<View>>, onCompleteAction: Consumer<View>, errorAction: Consumer<Throwable>) {
        subscriptions.add(flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onResult(it) }, { errorAction.accept(it) }, { onResult(onCompleteAction) }))
    }

    fun subscribeTo(single: Single<Consumer<View>>, errorAction: Consumer<Throwable>) {
        subscriptions.add(single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onResult(it) }, { errorAction.accept(it) }))
    }

    fun subscribeTo(completable: Completable, onCompleteAction: Consumer<View>, errorAction: Consumer<Throwable>) {
        subscriptions.add(completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ onResult(onCompleteAction) }, { errorAction.accept(it) }))
    }

    private fun onResult(resultAction: Consumer<View>) {
        if (isPaused) {
            synchronized(queueLock) {
                viewActions.add(resultAction)
            }
        } else {
            viewActionSubject.onNext(resultAction)
        }
    }

    fun pause() {
        isPaused = true
    }

    fun resume() {
        isPaused = false
        consumeQueue()
    }

    fun destroy() {
        subscriptions.dispose()
        viewActionSubject.onComplete()
    }

    private fun consumeQueue() {
        synchronized(queueLock) {
            val actionIterator = viewActions.iterator()
            while (actionIterator.hasNext()) {
                val pendingViewAction = actionIterator.next()
                viewActionSubject.onNext(pendingViewAction)
                actionIterator.remove()
            }
        }
    }

    fun viewActionsObservable(): Flowable<Consumer<View>> {
        return viewActionSubject
    }
}
