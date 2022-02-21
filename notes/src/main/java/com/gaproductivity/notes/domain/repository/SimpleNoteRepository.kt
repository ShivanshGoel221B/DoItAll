package com.gaproductivity.notes.domain.repository

import com.gaproductivity.database.entity.SimpleNote
import kotlinx.coroutines.flow.Flow

interface SimpleNoteRepository {
    fun getAllSimpleNotes(): Flow<List<SimpleNote>>
    suspend fun getSimpleNoteById(simpleNoteId: Int): SimpleNote?
    suspend fun createSimpleNote(simpleNote: SimpleNote)
    suspend fun updateSimpleNote(simpleNote: SimpleNote)
    suspend fun deleteSimpleNote(simpleNote: SimpleNote)
}