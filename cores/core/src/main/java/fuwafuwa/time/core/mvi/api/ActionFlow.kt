package fuwafuwa.time.core.mvi.api

import kotlinx.coroutines.flow.Flow

interface Action

interface ActionFlow<UiAction : Action> {

    val actions: Flow<UiAction>

    fun sendAction(action: UiAction)
}
