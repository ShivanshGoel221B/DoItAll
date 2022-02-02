package com.gaproductivity.database.di

import android.app.Application
import androidx.room.Room
import com.gaproductivity.database.Constants
import com.gaproductivity.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            Constants.DB_NAME
        ).build()
    }

}