package com.gaproductivity.components.presentation.di

import android.app.Application
import com.gaproductivity.components.presentation.utils.Reminder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ComponentModule {

    @Singleton
    @Provides
    fun provideReminder(
        app: Application
    ): Reminder {
        return Reminder(app)
    }
}