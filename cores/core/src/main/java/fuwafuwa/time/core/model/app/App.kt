package fuwafuwa.time.core.model.app

data class App(
    val name: String,
    val packageName: String,
    val processName: String,
    val apkSize: Long,
    val appSize: AppSize,
    val dataSize: DataSize,
    val cacheSize: CacheSize,
    val totalSize: Long,
    val iconPath: String,
    val versionName: String?,
    val versionCode: Long,
)
