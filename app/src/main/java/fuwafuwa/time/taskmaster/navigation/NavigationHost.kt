package fuwafuwa.time.taskmaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavScreen
import fuwafuwa.time.apps_info_impl.navigation.appsInfoNavRoot
import fuwafuwa.time.hub_api.HubNavScreen
import fuwafuwa.time.hub_impl.navigation.hubNavRoot

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppsInfoNavScreen
    ) {
        hubNavScenario(navController)
        appsInfoNavScenario(navController)
    }
}

private fun NavGraphBuilder.hubNavScenario(navController: NavController) {
    hubNavRoot(navController)
}

private fun NavGraphBuilder.appsInfoNavScenario(navController: NavController) {
    appsInfoNavRoot(navController)
}
