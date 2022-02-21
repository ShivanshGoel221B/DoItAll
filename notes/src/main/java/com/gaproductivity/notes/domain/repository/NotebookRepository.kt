package com.gaproductivity.notes.domain.repository

import com.gaproductivity.database.entity.NoteBook
import kotlinx.coroutines.flow.Flow

interface NotebookRepository {
    suspend fun createNotebook(noteBook: NoteBook)
    fun getAllNotebooks(): Flow<List<NoteBook>>
    suspend fun getNotebookById(notebookId: Int): NoteBook?
    suspend fun updateNotebook(noteBook: NoteBook)
    suspend fun deleteNotebook(noteBook: NoteBook)
}