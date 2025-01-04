package fuwafuwa.time.taskmaster.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.dao.PermissionConfigDao
import fuwafuwa.time.core_data.db.TaskMasterDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext applicationContext: Context): TaskMasterDatabase {
            return Room.databaseBuilder(
                applicationContext,
                TaskMasterDatabase::class.java,
                "task_master_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Singleton
        @Provides
        fun provideAppDao(database: TaskMasterDatabase): AppDao {
            return database.getAppDao()
        }

        @Singleton
        @Provides
        fun providePermissionConfigDao(database: TaskMasterDatabase): PermissionConfigDao {
            return database.getPermissionConfigDao()
        }

        @Singleton
        @Provides
        fun provideApplicationContext(@ApplicationContext context: Context): Context {
            return context
        }

        @Singleton
        @Provides
        fun provideDefaultCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.Default)
        }
    }
}
