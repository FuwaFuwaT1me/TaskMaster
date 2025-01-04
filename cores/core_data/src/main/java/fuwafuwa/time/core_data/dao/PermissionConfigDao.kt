package fuwafuwa.time.core_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fuwafuwa.time.core_data.entity.permission.PermissionConfigDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PermissionConfigDao {

    @Query("SELECT * FROM PermissionConfigDto LIMIT 1")
    fun getPermissionConfig(): Flow<PermissionConfigDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putPermissionConfig(permissionConfigDto: PermissionConfigDto)
}