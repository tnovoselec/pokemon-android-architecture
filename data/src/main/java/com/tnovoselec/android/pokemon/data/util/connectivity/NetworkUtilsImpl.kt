package com.tnovoselec.android.pokemon.data.util.connectivity

import io.reactivex.Single
import java.net.InetAddress
import java.net.UnknownHostException


class NetworkUtilsImpl(private val connectivityManagerWrapper: ConnectivityManagerWrapper) : NetworkUtils {

    override val isConnectedToInternet: Single<Boolean>
        get() = Single.fromCallable({ isConnectedToNetwork && canResolveAddress(PING_ADDRESS) })

    override val activeNetworkData: Single<NetworkData>
        get() {
            return Single.fromCallable({ connectivityManagerWrapper.networkData })
        }

    private fun canResolveAddress(url: String): Boolean {
        return pingAddress(url)
    }

    private val isConnectedToNetwork: Boolean
        get() = connectivityManagerWrapper.isConnectedToNetwork

    private fun pingAddress(url: String): Boolean {
        try {
            val address = InetAddress.getByName(url)
            return address != null && EMPTY != address!!.getHostAddress()
        } catch (e: UnknownHostException) {
            return false
        }

    }

    companion object {

        private val EMPTY = ""
        private val PING_ADDRESS = "www.google.com"
    }
}