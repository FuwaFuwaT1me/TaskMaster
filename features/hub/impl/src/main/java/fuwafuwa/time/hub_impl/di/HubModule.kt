package fuwafuwa.time.hub_impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fuwafuwa.time.hub_impl.ScreensProvider
import fuwafuwa.time.hub_impl.mvi.HubState
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface HubModule {

    companion object {

        @Singleton
        @Provides
        fun provideDefaultState(): HubState {
            return HubState(
                hubScreenInfos = ScreensProvider().get()
            )
        }
    }
}