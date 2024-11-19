package fuwafuwa.time.core.mvi.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Model<UiState, UiAction, NavEvent>
    where UiState : State,
          UiAction : Action,
          NavEvent : NavigationEvent {


    val state: StateFlow<UiState>
    val navigationEvent: Flow<NavEvent>

    fun onAction(action: UiAction)

    fun sendNavigationEvent(navEvent: NavEvent)

    fun clean()
}
