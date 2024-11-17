package fuwafuwa.time.core.mvi.impl

import androidx.annotation.CallSuper
import fuwafuwa.time.core.mvi.api.Action
import fuwafuwa.time.core.mvi.api.Model
import fuwafuwa.time.core.mvi.api.NavigationEvent
import fuwafuwa.time.core.mvi.api.NavigationEventFlow
import fuwafuwa.time.core.mvi.api.State
import fuwafuwa.time.core.mvi.api.UiStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseModel<UiState, UiAction, NavEvent>(
    defaultViewState: UiState,
    protected val scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
    private val uiStateFlow: UiStateFlow<UiState> = BaseUiStateFlow(defaultViewState, scope),
    private val navigationEventFlow: NavigationEventFlow<NavEvent> = BaseNavigationEventFlow(scope)
) : Model<UiState, UiAction, NavEvent>
    where UiState : State,
          UiAction : Action,
          NavEvent : NavigationEvent {

    override val state: StateFlow<UiState>
        get() = uiStateFlow.state
    override val navigationEvent: Flow<NavEvent>
        get() = navigationEventFlow.navigationEvent

    override fun sendNavigationEvent(navEvent: NavEvent) {
        navigationEventFlow.sendNavigationEvent(navEvent)
    }

    protected fun updateState(updateState: UiState.() -> UiState) {
        uiStateFlow.updateState(updateState)
    }

    @CallSuper
    override fun clean() {
        //TODO: cancel() or cancelChildren()
        scope.cancel()
    }
}
