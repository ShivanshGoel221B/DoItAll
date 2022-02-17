package com.gaproductivity.simple_note.di

import com.gaproductivity.database.Database
import com.gaproductivity.simple_note.domain.repository.SimpleNoteRepository
import com.gaproductivity.simple_note.repository.SimpleNoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SimpleNoteModule {

    @Provides
    @Singleton
    fun getSimpleNoteRepository(database: Database): SimpleNoteRepository {
        return SimpleNoteRepositoryImpl(database.simpleNoteDao)
    }

}