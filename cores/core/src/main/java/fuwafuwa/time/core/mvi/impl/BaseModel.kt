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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseModel<UiState, UiAction>(
    defaultViewState: UiState,
    protected val scope: CoroutineScope = CoroutineScope(Dispatchers.Default.limitedParallelism(1)),
    private val uiStateFlow: UiStateFlow<UiState> = BaseUiStateFlow(defaultViewState),
    private val navigationEventFlow: NavigationEventFlow<BaseNavigationEvent> = BaseNavigationEventFlow(scope)
) : Model<UiState, UiAction, BaseNavigationEvent>
    where UiState : State,
          UiAction : Action {

    override val state: StateFlow<UiState>
        get() = uiStateFlow.state
    override val navigationEvent: Flow<BaseNavigationEvent>
        get() = navigationEventFlow.navigationEvent

    override fun sendNavigationEvent(navEvent: BaseNavigationEvent) {
        navigationEventFlow.sendNavigationEvent(navEvent)
    }

    protected fun updateState(updateState: UiState.() -> UiState): Job {
        return uiStateFlow.updateState(updateState)
    }

    @CallSuper
    override fun clean() {
        //TODO: cancel() or cancelChildren()
        scope.cancel()
    }
}
