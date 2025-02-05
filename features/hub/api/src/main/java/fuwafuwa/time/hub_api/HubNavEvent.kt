package fuwafuwa.time.hub_api

import fuwafuwa.time.core.model.DataBundle
import fuwafuwa.time.core.model.Screen
import fuwafuwa.time.core.mvi.impl.BaseNavigationEvent

data class HubNavEvent(
    override val dataBundle: DataBundle,
    override val screen: Screen = HubNavScreen
) : BaseNavigationEvent.NavigateTo