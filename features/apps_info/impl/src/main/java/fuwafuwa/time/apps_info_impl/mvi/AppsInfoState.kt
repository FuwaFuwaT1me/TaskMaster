package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core.model.permission.PermissionConfig
import fuwafuwa.time.core.mvi.api.State
import fuwafuwa.time.core_data.entity.sorting.AppSortingProperty

data class AppsInfoState(
    val apps: List<App>,
    val permissionConfig: PermissionConfig,
    val searchString: String,
    val sortingProperties: List<AppSortingProperty>,
    val filteredApps: List<App>,
    val searchInProgress: Boolean,
    val sortedApps: List<App>
) : State