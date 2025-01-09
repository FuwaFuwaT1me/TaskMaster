package fuwafuwa.time.apps_info_impl.mvi

import androidx.activity.ComponentActivity
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.apps_info_impl.usecase.SearchForAppsUseCase
import fuwafuwa.time.core.mvi.impl.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsInfoViewModel @Inject constructor(
    override val model: AppsInfoModel
) : BaseViewModel<AppsInfoAction, AppsInfoState, AppsInfoNavEvent>() {

    fun updateApps() {
        viewModelScope.launch(Dispatchers.IO) {
            model.onAction(UpdateAppsAction)
        }
    }

    fun grantUsageStatsPermission(activity: ComponentActivity) {
        viewModelScope.launch {
            model.onAction(GrantUsageStatsPermission(activity))
        }
    }

    fun searchForApps(searchString: String) {
        viewModelScope.launch {
            model.onAction(SearchForApps(searchString))
        }
    }
}
