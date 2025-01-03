package fuwafuwa.time.core_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.entity.app.AppDto

@Database(entities = [
    AppDto::class
], version = 4)
abstract class TaskMasterDatabase: RoomDatabase() {

    abstract fun getAppDao(): AppDao
}
