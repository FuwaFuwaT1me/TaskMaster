package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core.model.permission.PermissionConfig
import fuwafuwa.time.core.mvi.api.State

data class AppsInfoState(
    val apps: List<App>,
    val permissionConfig: PermissionConfig
) : State