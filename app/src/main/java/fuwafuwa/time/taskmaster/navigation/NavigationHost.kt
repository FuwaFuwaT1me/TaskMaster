package fuwafuwa.time.taskmaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavScreen
import fuwafuwa.time.apps_info_impl.navigation.appsInfoNavRoot

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppsInfoNavScreen
    ) {
        backStackReaderScenario(navController)
    }
}

private fun NavGraphBuilder.backStackReaderScenario(navController: NavController) {
    appsInfoNavRoot(navController)
}