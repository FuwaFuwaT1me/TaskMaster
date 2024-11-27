package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.core.mvi.api.Model
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class AppsInfoModel : Model<AppsInfoState, AppsInfoAction, AppsInfoNavEvent> {

    override val state: StateFlow<AppsInfoState>
        get() = TODO("Not yet implemented")
    override val navigationEvent: Flow<AppsInfoNavEvent>
        get() = TODO("Not yet implemented")

    override fun sendNavigationEvent(navEvent: AppsInfoNavEvent) {
        TODO("Not yet implemented")
    }

    override fun onAction(action: AppsInfoAction) {
        TODO("Not yet implemented")
    }

    override fun clean() {
        TODO("Not yet implemented")
    }
}