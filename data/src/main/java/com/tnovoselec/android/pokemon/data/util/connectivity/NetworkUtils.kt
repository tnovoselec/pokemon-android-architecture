package com.tnovoselec.android.pokemon.data.util.connectivity

import io.reactivex.Single


interface NetworkUtils {

    val isConnectedToInternet: Single<Boolean>

    val activeNetworkData: Single<NetworkData>
}