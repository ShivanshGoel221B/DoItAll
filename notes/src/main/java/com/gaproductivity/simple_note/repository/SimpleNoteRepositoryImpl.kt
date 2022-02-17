package com.gaproductivity.simple_note.repository

import com.gaproductivity.database.dao.SimpleNoteDao
import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.simple_note.domain.repository.SimpleNoteRepository
import kotlinx.coroutines.flow.Flow

class SimpleNoteRepositoryImpl(
    private val simpleNoteDao: SimpleNoteDao
): SimpleNoteRepository {

    override fun getAllSimpleNotes(): Flow<List<SimpleNote>> {
        return simpleNoteDao.getAllSimpleNotes()
    }

    override suspend fun getSimpleNoteById(simpleNoteId: Int): SimpleNote? {
        return simpleNoteDao.getSimpleNoteById(simpleNoteId)
    }

    override suspend fun createSimpleNote(simpleNote: SimpleNote) {
        simpleNoteDao.createSimpleNote(simpleNote)
    }

    override suspend fun updateSimpleNote(simpleNote: SimpleNote) {
        simpleNoteDao.updateSimpleNote(simpleNote)
    }

    override suspend fun deleteSimpleNote(simpleNote: SimpleNote) {
        simpleNoteDao.deleteTodoTask(simpleNote)
    }
}