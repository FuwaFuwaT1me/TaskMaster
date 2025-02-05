package fuwafuwa.time.hub_impl

class ScreensProvider {

    fun get(): List<HubScreenInfo> = listOf(
        HubScreenInfo.AppsInfo,
        HubScreenInfo.Processes
    )
}