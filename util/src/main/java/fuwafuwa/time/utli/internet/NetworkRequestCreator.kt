package fuwafuwa.time.utli.internet

import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkRequestCreator {

    fun create(): NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
}