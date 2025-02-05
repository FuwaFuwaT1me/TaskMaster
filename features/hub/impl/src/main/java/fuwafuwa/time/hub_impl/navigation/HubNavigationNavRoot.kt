package fuwafuwa.time.hub_impl.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fuwafuwa.time.core_compose.BaseScreen
import fuwafuwa.time.hub_impl.ui.HubScreen
import fuwafuwa.time.hub_api.HubNavScreen
import fuwafuwa.time.hub_impl.mvi.HubViewModel

fun NavGraphBuilder.hubNavRoot(
    navController: NavController
) {
    composable<HubNavScreen> {
        val viewModel = hiltViewModel<HubViewModel>()

        LaunchedEffect(Unit) {
            viewModel.init()
        }

        BaseScreen(
            navController = navController,
            viewModel = viewModel
        ) {
            HubScreen(viewModel)
        }
    }
}