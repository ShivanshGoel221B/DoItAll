package com.gaproductivity.database.dao

import androidx.room.*
import com.gaproductivity.database.Constants
import com.gaproductivity.database.entity.NoteBook
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteBookDao {

    @Query("SELECT * FROM ${Constants.NOTE_BOOK}")
    fun getAllNoteBooks(): Flow<List<NoteBook>>

    @Query("SELECT * FROM ${Constants.NOTE_BOOK} WHERE noteBookId = :noteBookId")
    suspend fun getNoteBookById(noteBookId: Int): NoteBook?

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = NoteBook::class)
    suspend fun createNoteBook(noteBook: NoteBook)

    @Update(entity = NoteBook::class)
    suspend fun updateNoteBook(noteBook: NoteBook)

    @Delete(entity = NoteBook::class)
    suspend fun deleteTodoTask(noteBook: NoteBook)
}