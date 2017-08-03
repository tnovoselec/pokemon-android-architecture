package com.tnovoselec.android.pokemon.data.util.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.processors.PublishProcessor


class ConnectivityReceiverImpl(context: Context, private val networkUtils: NetworkUtils, private val backgroundScheduler: Scheduler) : BroadcastReceiver(), ConnectivityReceiver {
    private val subject: PublishProcessor<Boolean>?

    private var isConnected: Boolean = false

    init {
        val intentFilter = IntentFilter(ACTION_CONNECTIVITY_CHANGE)
        context.registerReceiver(this, intentFilter)
        this.subject = PublishProcessor.create<Boolean>()
    }

    override val connectivityStatus: Flowable<Boolean>
        get() = subject!!.subscribeOn(backgroundScheduler)
                .observeOn(backgroundScheduler)

    override fun isConnected(): Single<Boolean> {
        return networkUtils.isConnectedToInternet
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (subject == null) {
            return
        }

        checkConnectionStatus()
    }

    private fun checkConnectionStatus() {
        networkUtils.isConnectedToInternet
                .subscribeOn(backgroundScheduler)
                .observeOn(backgroundScheduler)
                .subscribe({ this.onNetworkStatus(it) }, { this.onNetworkStatusError(it) })
    }

    private fun onNetworkStatus(isConnected: Boolean?) {
        if (this.isConnected != isConnected) {
            this.isConnected = isConnected!!
            subject!!.onNext(isConnected)
        }
    }

    private fun onNetworkStatusError(throwable: Throwable) {
        // No-op
    }

    companion object {

        private val TAG = ConnectivityReceiverImpl::class.java.simpleName

        private val ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
    }
}