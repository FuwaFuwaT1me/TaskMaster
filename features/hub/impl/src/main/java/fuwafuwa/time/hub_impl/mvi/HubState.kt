package fuwafuwa.time.hub_impl.mvi

import fuwafuwa.time.core.mvi.api.State
import fuwafuwa.time.hub_impl.HubScreenInfo

data class HubState(
    val hubScreenInfos: List<HubScreenInfo>
) : State