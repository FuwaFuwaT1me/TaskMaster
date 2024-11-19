package fuwafuwa.time.core_data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
], version = 2)
abstract class TaskMasterDatabase: RoomDatabase() {
}
