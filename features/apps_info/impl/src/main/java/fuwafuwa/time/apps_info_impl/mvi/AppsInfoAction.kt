package fuwafuwa.time.apps_info_impl.mvi

import androidx.activity.ComponentActivity
import fuwafuwa.time.core.mvi.api.Action

sealed interface AppsInfoAction : Action

data object GetAppsAction : AppsInfoAction

data class GrantUsageStatsPermission(
    val activity: ComponentActivity
) : AppsInfoAction