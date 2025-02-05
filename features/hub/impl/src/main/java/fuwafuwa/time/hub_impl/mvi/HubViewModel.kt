package fuwafuwa.time.hub_impl.mvi

import dagger.hilt.android.lifecycle.HiltViewModel
import fuwafuwa.time.core.mvi.impl.BaseNavigationEvent
import fuwafuwa.time.core.mvi.impl.BaseViewModel
import fuwafuwa.time.hub_api.HubNavEvent
import javax.inject.Inject

@HiltViewModel
class HubViewModel @Inject constructor(
    override val model: HubModel
) : BaseViewModel<HubAction, HubState>() {


}