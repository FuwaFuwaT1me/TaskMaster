package fuwafuwa.time.core_data.entity.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
