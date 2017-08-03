package com.tnovoselec.android.pokemon.data.util.connectivity


interface ConnectivityManagerWrapper {

    val isConnectedToNetwork: Boolean

    val networkData: NetworkData
}
