package com.gaproductivity.notes.repository

import com.gaproductivity.database.dao.NoteBookDao
import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.notes.domain.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow

class NotebookRepositoryImpl(
    private val noteBookDao: NoteBookDao
): NotebookRepository {

    override suspend fun createNotebook(noteBook: NoteBook) {
        noteBookDao.createNoteBook(noteBook)
    }

    override fun getAllNotebooks(): Flow<List<NoteBook>> {
        return noteBookDao.getAllNoteBooks()
    }

    override suspend fun getNotebookById(notebookId: Int): NoteBook? {
        return noteBookDao.getNoteBookById(notebookId)
    }

    override suspend fun updateNotebook(noteBook: NoteBook) {
        noteBookDao.updateNoteBook(noteBook)
    }

    override suspend fun deleteNotebook(noteBook: NoteBook) {
        noteBookDao.deleteTodoTask(noteBook)
    }
}