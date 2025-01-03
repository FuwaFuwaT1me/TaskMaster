package fuwafuwa.time.utli.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

class NetworkCallbackImpl(
    private val connectivityManager: ConnectivityManager,
    private val trySend: (result: Boolean) -> Unit
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        connectivityManager.getNetworkCapabilities(network)?.let {
            if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                 trySend(true)
            }
        }
    }

    override fun onLost(network: Network) {
         trySend(false)
    }

    override fun onUnavailable() {
         trySend(false)
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
             trySend(true)
        } else {
             trySend(false)
        }
    }
}