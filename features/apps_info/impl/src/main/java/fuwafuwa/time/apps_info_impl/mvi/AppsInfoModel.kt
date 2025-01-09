package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.apps_info_impl.usecase.UpdateAppsUseCase
import fuwafuwa.time.apps_info_impl.usecase.GetPermissionConfigUseCase
import fuwafuwa.time.apps_info_impl.usecase.SearchForAppsUseCase
import fuwafuwa.time.core.mvi.impl.BaseModel
import fuwafuwa.time.core_data.entity.app.toModel
import fuwafuwa.time.core_data.entity.permission.toModel
import fuwafuwa.time.utli.permission.UsageStatsPermission
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppsInfoModel @Inject constructor(
    defaultViewState: AppsInfoState,
    private val updateAppsUseCase: UpdateAppsUseCase,
    private val getPermissionConfigUseCase: GetPermissionConfigUseCase,
    private val searchForAppsUseCase: SearchForAppsUseCase,
) : BaseModel<AppsInfoState, AppsInfoAction, AppsInfoNavEvent>(
    defaultViewState
) {

    init {
        scope.launch {
            updateAppsUseCase.apps.collect { apps ->
                updateState { copy(apps = apps.map { it.toModel() }) }
            }
        }

        scope.launch {
            getPermissionConfigUseCase.permissionConfig.collect { permissionConfig ->
                if (permissionConfig != null) {
                    updateState { copy(permissionConfig = permissionConfig.toModel()) }

                    if (permissionConfig.usageStatsPermission) {
                        onAction(UpdateAppsAction)
                    }
                }
            }
        }
    }

    override fun onAction(action: AppsInfoAction) {
        when (action) {
            UpdateAppsAction -> scope.launch {
                updateAppsUseCase.updateApps()
            }

            is GrantUsageStatsPermission -> scope.launch {
                UsageStatsPermission.grantIfNeeded(action.activity)
            }

            is SearchForApps -> scope.launch {
                updateState { copy(searchInProgress = true) }
                val searchedApps = searchForAppsUseCase.search(action.searchString)
                updateState {
                    copy(
                        searchString = action.searchString,
                        filteredApps = searchedApps,
                        searchInProgress = false
                    )
                }
            }
        }
    }
}