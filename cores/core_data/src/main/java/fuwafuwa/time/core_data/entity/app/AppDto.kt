package fuwafuwa.time.core_data.entity.app

import androidx.room.Entity
import androidx.room.PrimaryKey
import fuwafuwa.time.core.model.app.AppSize
import fuwafuwa.time.core.model.app.CacheSize
import fuwafuwa.time.core.model.app.DataSize

@Entity
data class AppDto(
    @PrimaryKey val packageName: String,
    val name: String,
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
