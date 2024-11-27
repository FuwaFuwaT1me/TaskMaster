package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.apps_info_api.navigation.AppsInfoNavEvent
import fuwafuwa.time.core.mvi.api.Model
import fuwafuwa.time.core.mvi.impl.BaseViewModel

class AppsInfoViewModel(
    override val model: Model<AppsInfoState, AppsInfoAction, AppsInfoNavEvent>
) : BaseViewModel<AppsInfoAction, AppsInfoState, AppsInfoNavEvent>() {


}
