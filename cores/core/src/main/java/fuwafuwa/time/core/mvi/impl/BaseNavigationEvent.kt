package fuwafuwa.time.core.mvi.impl

import fuwafuwa.time.core.model.DataBundle
import fuwafuwa.time.core.model.Screen
import fuwafuwa.time.core.mvi.api.NavigationEvent

sealed interface BaseNavigationEvent : NavigationEvent {

    interface NavigateTo : BaseNavigationEvent {
        val screen: Screen
        val dataBundle: DataBundle
    }

    interface NavigateBackTo : BaseNavigationEvent {
        val route: String
    }

    data object NavigateBack : BaseNavigationEvent
}
