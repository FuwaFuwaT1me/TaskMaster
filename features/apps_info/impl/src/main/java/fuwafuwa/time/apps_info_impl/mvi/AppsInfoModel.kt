package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.apps_info_impl.usecase.SortingAppsUseCase
import fuwafuwa.time.apps_info_impl.usecase.UpdateAppsUseCase
import fuwafuwa.time.apps_info_impl.usecase.GetPermissionConfigUseCase
import fuwafuwa.time.apps_info_impl.usecase.SearchForAppsUseCase
import fuwafuwa.time.core.model.app.App
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
    private val sortingAppsUseCase: SortingAppsUseCase
) : BaseModel<AppsInfoState, AppsInfoAction, AppsInfoNavEvent>(
    defaultViewState
) {

    init {
        scope.launch {
            updateAppsUseCase.apps.collect { apps ->
                updateState {
                    val modelApps = apps.map { it.toModel() }
                    copy(
                        apps = modelApps,
                        alteredApps = modelApps
                    )
                }
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
                val searchStartState = updateState {
                    copy(
                        searchInProgress = true,
                        searchString = action.searchString
                    )
                }
                searchStartState.join()

                val searchedApps = state.value.apps
                    .applySearch()
                    .applyFilters()

                updateState {
                    copy(
                        alteredApps = searchedApps,
                        searchInProgress = false
                    )
                }
            }

            is ChangeSortingProperty -> scope.launch {
                updateState {
                    copy(sortingProperties = action.sortingProperties).also {
                        onAction(SortApps)
                    }
                }
            }

            is SortApps -> scope.launch {
                updateState {
                    val sortedApps = state.value.apps
                        .applyFilters()
                        .applySearch()

                    copy(alteredApps = sortedApps)
                }
            }

            is ShowAppSizeDialog -> scope.launch {
                updateState {
                    copy(
                        isAppSizeDialogDisplayed = true,
                        appSizeDialogApp = action.app
                    )
                }
            }

            HideAppSizeDialog -> scope.launch {
                updateState {
                    copy(
                        isAppSizeDialogDisplayed = false,
                        appSizeDialogApp = null
                    )
                }
            }
        }
    }

    private fun List<App>.applyFilters(): List<App> {
        return sortingAppsUseCase.sort(this, state.value.sortingProperties)
    }

    private fun List<App>.applySearch(): List<App> {
        return searchForAppsUseCase.search(this, state.value.searchString)
    }
}