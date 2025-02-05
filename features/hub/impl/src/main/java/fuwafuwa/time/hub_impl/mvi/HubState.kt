package fuwafuwa.time.hub_impl.mvi

import fuwafuwa.time.core.mvi.api.State
import fuwafuwa.time.hub.Screen

data class HubState(
    val screens: List<Screen>
) : State