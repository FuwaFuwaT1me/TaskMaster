package fuwafuwa.time.utli.network

import android.net.NetworkCapabilities
import android.net.NetworkRequest
import javax.inject.Inject

class NetworkRequestCreator @Inject constructor() {

    fun create(): NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
}