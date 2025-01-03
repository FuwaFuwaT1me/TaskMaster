package fuwafuwa.time.apps_info_impl.di

import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fuwafuwa.time.apps_info_impl.mvi.AppsInfoState
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AppsInfoModule {

    companion object {

        @Singleton
        @Provides
        fun provideDefaultState(): AppsInfoState {
            return AppsInfoState(
                apps = emptyList()
            )
        }

        @Singleton
        @Provides
        fun providePackageManager(@ApplicationContext context: Context): PackageManager {
            return context.packageManager
        }
    }
}