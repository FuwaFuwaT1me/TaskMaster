package fuwafuwa.time.apps_info_impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    }
}