package fuwafuwa.time.core_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fuwafuwa.time.core_data.entity.app.AppDto
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM AppDto")
    fun getApps(): Flow<List<AppDto>>

    @Query("SELECT * FROM AppDto WHERE packageName LIKE '%' || :substring || '%' OR name LIKE '%' || :substring || '%'")
    suspend fun getAppsBySubstring(substring: String): List<AppDto>

    @Insert
    suspend fun putApp(app: AppDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putApps(apps: List<AppDto>)
}