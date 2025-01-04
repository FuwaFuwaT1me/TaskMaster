package fuwafuwa.time.core_data.entity.app

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val packageName: String,
    val processName: String,
    val apkSize: Double,
    val appSize: Double,
    val dataSize: Double,
    val cacheSize: Double,
    val iconPath: String
)
