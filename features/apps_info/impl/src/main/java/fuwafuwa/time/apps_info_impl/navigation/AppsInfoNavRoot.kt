package fuwafuwa.time.apps_info_impl.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavScreen
import fuwafuwa.time.apps_info_impl.mvi.AppsInfoViewModel
import fuwafuwa.time.apps_info_impl.ui.AppsInfoScreen
import fuwafuwa.time.core_compose.BaseScreen

fun NavGraphBuilder.appsInfoNavRoot(
    navController: NavController,
) {
    composable<AppsInfoNavScreen> {
        val viewModel = hiltViewModel<AppsInfoViewModel>()

        LaunchedEffect(Unit) {
            viewModel.init()
        }

        BaseScreen(
            navController = navController,
            viewModel = viewModel
        ) {
            AppsInfoScreen(viewModel)
        }
    }
}