package fuwafuwa.time.apps_info_impl.mvi

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.core.mvi.api.Model
import fuwafuwa.time.core.mvi.impl.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsInfoViewModel @Inject constructor(
    override val model: AppsInfoModel
) : BaseViewModel<AppsInfoAction, AppsInfoState, AppsInfoNavEvent>() {

    fun getApps() {
        viewModelScope.launch(Dispatchers.IO) {
            model.onAction(GetAppsAction)
        }
    }

    fun grantUsageStatsPermission(activity: ComponentActivity) {
        viewModelScope.launch {
            model.onAction(GrantUsageStatsPermission(activity))
        }
    }
}
