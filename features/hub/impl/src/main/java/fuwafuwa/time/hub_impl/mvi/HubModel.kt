package fuwafuwa.time.hub_impl.mvi

import fuwafuwa.time.core.mvi.impl.BaseModel
import fuwafuwa.time.hub_api.HubNavEvent
import javax.inject.Inject

class HubModel @Inject constructor(
    defaultViewState: HubState,
) : BaseModel<HubState, HubAction, HubNavEvent>(
    defaultViewState
) {

    override fun onAction(action: HubAction) {

    }
}