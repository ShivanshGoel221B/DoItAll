package com.gaproductivity.doitall.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.gaproductivity.doitall.presentation.preferenceStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferencesDatastore(
        app: Application
    ): DataStore<Preferences> {
        return app.applicationContext.preferenceStore
    }

}