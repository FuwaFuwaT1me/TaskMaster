package fuwafuwa.time.utli.di

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface UtilModule {

    companion object {

        @Singleton
        @Provides
        fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
            return context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        }
    }
}