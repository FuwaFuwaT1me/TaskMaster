package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.apps_info_impl.usecase.GetAppsUseCase
import fuwafuwa.time.apps_info_impl.usecase.GetPermissionConfigUseCase
import fuwafuwa.time.core.mvi.impl.BaseModel
import fuwafuwa.time.core_data.entity.app.toModel
import fuwafuwa.time.core_data.entity.permission.toModel
import fuwafuwa.time.utli.permission.UsageStatsPermission
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppsInfoModel @Inject constructor(
    defaultViewState: AppsInfoState,
    private val getAppsUseCase: GetAppsUseCase,
    private val getPermissionConfigUseCase: GetPermissionConfigUseCase,
) : BaseModel<AppsInfoState, AppsInfoAction, AppsInfoNavEvent>(
    defaultViewState
) {

    init {
        scope.launch {
            getAppsUseCase.apps.collect { apps ->
                updateState { copy(apps = apps.map { it.toModel() }) }
            }
        }

        scope.launch {
            getPermissionConfigUseCase.permissionConfig.collect { permissionConfig ->
                if (permissionConfig != null) {
                    updateState { copy(permissionConfig = permissionConfig.toModel()) }
                    if (permissionConfig.usageStatsPermission) {
                        onAction(GetAppsAction)
                    }
                }
            }
        }
    }

    override fun onAction(action: AppsInfoAction) {
        when (action) {
            GetAppsAction -> scope.launch {
                getAppsUseCase.getApps()
            }

            is GrantUsageStatsPermission -> scope.launch {
                UsageStatsPermission.grantIfNeeded(action.activity)
            }
        }
    }
}