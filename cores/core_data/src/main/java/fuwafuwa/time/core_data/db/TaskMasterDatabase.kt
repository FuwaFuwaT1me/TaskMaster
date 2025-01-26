package fuwafuwa.time.core_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fuwafuwa.time.core_data.converter.Converters
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.dao.PermissionConfigDao
import fuwafuwa.time.core_data.entity.app.AppDto
import fuwafuwa.time.core_data.entity.permission.PermissionConfigDto

@Database(entities = [
    AppDto::class,
    PermissionConfigDto::class,
], version = 13)
@TypeConverters(Converters::class)
abstract class TaskMasterDatabase: RoomDatabase() {

    abstract fun getAppDao(): AppDao

    abstract fun getPermissionConfigDao(): PermissionConfigDao
}
