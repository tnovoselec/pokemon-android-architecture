package com.tnovoselec.android.pokemon.data.util.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE


class ConnectivityManagerWrapperImpl(context: Context) : ConnectivityManagerWrapper {

    private val connectivityManager: ConnectivityManager

    init {
        this.connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override val isConnectedToNetwork: Boolean
        get() {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    override val networkData: NetworkData
        get() {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            val hasInternetConnection = activeNetworkInfo != null && activeNetworkInfo.isConnected
            val isMobileConnection = activeNetworkInfo != null && activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            return NetworkData(hasInternetConnection, isMobileConnection)
        }
}