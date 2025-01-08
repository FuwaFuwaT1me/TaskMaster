package fuwafuwa.time.core.model.app

data class App(
    val name: String?,
    val packageName: String,
    val processName: String,
    val apkSize: Long,
    val appSize: Long,
    val dataSize: Long,
    val cacheSize: Long,
    val iconPath: String,
    val versionName: String?,
    val versionCode: Long,
)
