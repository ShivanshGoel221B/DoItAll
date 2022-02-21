package com.gaproductivity.notes.di

import com.gaproductivity.database.Database
import com.gaproductivity.notes.domain.repository.NotebookRepository
import com.gaproductivity.notes.domain.repository.SimpleNoteRepository
import com.gaproductivity.notes.repository.NotebookRepositoryImpl
import com.gaproductivity.notes.repository.SimpleNoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun getSimpleNoteRepository(database: Database): SimpleNoteRepository {
        return SimpleNoteRepositoryImpl(database.simpleNoteDao)
    }

    @Provides
    @Singleton
    fun getNotebookRepository(database: Database): NotebookRepository {
        return NotebookRepositoryImpl(database.noteBookDao)
    }

}