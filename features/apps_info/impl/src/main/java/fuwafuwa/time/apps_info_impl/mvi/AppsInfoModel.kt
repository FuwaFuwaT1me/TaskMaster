package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.apps_info_impl.usecase.GetAppsUseCase
import fuwafuwa.time.core.mvi.impl.BaseModel
import fuwafuwa.time.core_data.entity.app.toModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppsInfoModel @Inject constructor(
    defaultViewState: AppsInfoState,
    private val getAppsUseCase: GetAppsUseCase
) : BaseModel<AppsInfoState, AppsInfoAction, AppsInfoNavEvent>(
    defaultViewState
) {

    init {
        scope.launch {
            getAppsUseCase.apps.collect { apps ->
                updateState { copy(apps = apps.map { it.toModel() }) }
            }
        }
    }

    override fun onAction(action: AppsInfoAction) {
        when (action) {
            GetAppsAction -> scope.launch {
                getAppsUseCase.getApps()
            }
        }
    }
}