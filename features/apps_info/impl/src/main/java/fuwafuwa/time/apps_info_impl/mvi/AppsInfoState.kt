package fuwafuwa.time.apps_info_impl.mvi

import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core.mvi.api.State

data class AppsInfoState(
    val apps: List<App>
) : State