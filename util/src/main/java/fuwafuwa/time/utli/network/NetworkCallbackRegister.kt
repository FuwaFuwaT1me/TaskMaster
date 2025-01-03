package fuwafuwa.time.utli.network

import android.net.ConnectivityManager
import android.net.NetworkRequest
import javax.inject.Inject

class NetworkCallbackRegister @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {

    fun register(
        networkRequest: NetworkRequest,
        networkCallback: ConnectivityManager.NetworkCallback
    ) {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}