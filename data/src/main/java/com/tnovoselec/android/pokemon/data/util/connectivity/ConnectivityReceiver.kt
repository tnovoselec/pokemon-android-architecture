package com.tnovoselec.android.pokemon.data.util.connectivity

import io.reactivex.Flowable
import io.reactivex.Single


interface ConnectivityReceiver {

    val connectivityStatus: Flowable<Boolean>

    fun isConnected(): Single<Boolean>
}
