package fuwafuwa.time.core_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.dao.PermissionConfigDao
import fuwafuwa.time.core_data.entity.app.AppDto
import fuwafuwa.time.core_data.entity.permission.PermissionConfigDto

@Database(entities = [
    AppDto::class,
    PermissionConfigDto::class,
], version = 7)
abstract class TaskMasterDatabase: RoomDatabase() {

    abstract fun getAppDao(): AppDao

    abstract fun getPermissionConfigDao(): PermissionConfigDao
}
