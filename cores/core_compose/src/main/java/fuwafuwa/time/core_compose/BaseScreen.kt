package fuwafuwa.time.core_compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import fuwafuwa.time.core.mvi.api.Action
import fuwafuwa.time.core.mvi.api.NavigationEvent
import fuwafuwa.time.core.mvi.api.State
import fuwafuwa.time.core.mvi.impl.BaseNavigationEvent
import fuwafuwa.time.core.mvi.impl.BaseViewModel

@Composable
fun BaseScreen(
    navController: NavController,
    viewModel: BaseViewModel<out Action, out State, out NavigationEvent>,
    content: @Composable (NavController) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.model.navigationEvent.collect { navEvent ->
            when (navEvent) {
                is BaseNavigationEvent.NavigateTo -> {
                    navController.navigate(navEvent.screen)
                }
                is BaseNavigationEvent.NavigateBackTo -> {
                    navController.popBackStack(navEvent.route, false)
                }
                is BaseNavigationEvent.NavigateBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        content(navController)
    }
}