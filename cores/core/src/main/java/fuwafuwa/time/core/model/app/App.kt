package fuwafuwa.time.core.model.app

data class App(
    val name: String?,
    val packageName: String,
    val processName: String,
    val apkSize: Double,
    val appSize: Double,
    val dataSize: Double,
    val cacheSize: Double,
    val iconPath: String,
    val versionName: String?,
    val versionCode: Long,
)
