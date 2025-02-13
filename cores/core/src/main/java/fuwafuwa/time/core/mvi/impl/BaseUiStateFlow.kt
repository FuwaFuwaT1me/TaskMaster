package fuwafuwa.time.core.mvi.impl

import fuwafuwa.time.core.mvi.api.State
import fuwafuwa.time.core.mvi.api.UiStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BaseUiStateFlow<ViewState : State>(
    defaultViewState: ViewState,
) : UiStateFlow<ViewState> {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.Default.limitedParallelism(1)
    )

    private val _viewState = MutableStateFlow(defaultViewState)

    override val state: StateFlow<ViewState>
        get() = _viewState

    override fun updateState(updateState: ViewState.() -> ViewState): Job {
        return scope.launch {
            val newState = updateState(_viewState.value)
            _viewState.value = newState
        }
    }
}
