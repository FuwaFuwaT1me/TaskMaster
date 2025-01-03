package fuwafuwa.time.core.model.app

data class App(
    val name: String?,
    val packageName: String,
    val processName: String,
    val apkSize: Double,
    val appFolderSize: Double,
    val iconPath: String
)
