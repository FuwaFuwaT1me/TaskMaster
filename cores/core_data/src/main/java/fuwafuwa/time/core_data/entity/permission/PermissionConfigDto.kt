package fuwafuwa.time.core_data.entity.permission

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PermissionConfigDto(
    @PrimaryKey val id: Int = 0,
    val usageStatsPermission: Boolean
)
