package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.core.mvi.api.Action

sealed interface AppsInfoAction : Action

data object GetAppsAction : AppsInfoAction