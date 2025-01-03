package fuwafuwa.time.utli.network

import android.net.ConnectivityManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkObserver @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val networkRequestCreator: NetworkRequestCreator,
    private val networkCallbackRegister: NetworkCallbackRegister,
) {

    val isConnected: Flow<Boolean>
        get() = callbackFlow {
            val networkCallback = NetworkCallbackImpl(connectivityManager) { result ->
                trySend(result)
            }
            networkCallbackRegister.register(
                networkRequest = networkRequestCreator.create(),
                networkCallback = networkCallback
            )

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
}