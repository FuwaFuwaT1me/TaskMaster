package fuwafuwa.time.hub

class ScreensProvider {

    fun get(): List<Screen> = listOf(
        Screen.APPS_INFO,
        Screen.PROCESSES
    )
}