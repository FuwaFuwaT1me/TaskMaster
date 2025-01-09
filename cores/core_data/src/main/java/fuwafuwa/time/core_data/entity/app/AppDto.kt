package fuwafuwa.time.core_data.entity.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppDto(
    @PrimaryKey val packageName: String,
    val name: String?,
    val processName: String,
    val apkSize: Long,
    val appSize: Long,
    val dataSize: Long,
    val cacheSize: Long,
    val iconPath: String,
    val versionName: String?,
    val versionCode: Long,
)
