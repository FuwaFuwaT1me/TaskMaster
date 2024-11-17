package fuwafuwa.time.core.mvi.impl

import androidx.lifecycle.ViewModel
import fuwafuwa.time.core.mvi.api.Action
import fuwafuwa.time.core.mvi.api.ActionFlow
import fuwafuwa.time.core.mvi.api.Model
import fuwafuwa.time.core.mvi.api.NavigationEvent
import fuwafuwa.time.core.mvi.api.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiAction, UiState, NavEvent> : ViewModel()
        where UiAction : Action,
              UiState : State,
              NavEvent : NavigationEvent
{

    abstract val model: Model<UiState, UiAction, NavEvent>

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val actionFlow: ActionFlow<UiAction> = BaseActionFlow(scope)

    private val uiActions: Flow<UiAction> = actionFlow.actions

    fun init() {
        scope.coroutineContext.cancelChildren()
        setupCollecting()
    }

    fun sendAction(action: UiAction) {
        scope.launch {
            actionFlow.sendAction(action)
        }
    }

    fun sendNavigationEvent(navEvent: NavEvent) {
        model.sendNavigationEvent(navEvent)
    }

    private fun setupCollecting() {
        scope.launch {
            uiActions.collect { viewAction ->
                model.onAction(viewAction)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        //TODO: cancel() or cancelChildren()
        scope.cancel()
    }
}
