package fuwafuwa.time.apps_info_api.navigation

import fuwafuwa.time.core.model.Screen
import fuwafuwa.time.core.mvi.impl.BaseNavigationEvent

data class AppsInfoNavEvent(
    override val dataBundle: AppsInfoDataBundle = AppsInfoDataBundle(),
    override val screen: Screen = AppsInfoNavScreen,
) : BaseNavigationEvent.NavigateTo