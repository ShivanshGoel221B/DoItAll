package com.gaproductivity.database.dao

import androidx.room.*
import com.gaproductivity.database.Constants
import com.gaproductivity.database.entity.SimpleNote
import kotlinx.coroutines.flow.Flow

@Dao
interface SimpleNoteDao {

    @Query("SELECT * FROM ${Constants.SIMPLE_NOTES}")
    fun getAllSimpleNotes(): Flow<List<SimpleNote>>

    @Query("SELECT * FROM ${Constants.SIMPLE_NOTES} WHERE simpleNoteId = :simpleNoteId")
    suspend fun getSimpleNoteById(simpleNoteId: Int): SimpleNote?

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = SimpleNote::class)
    suspend fun createSimpleNote(simpleNote: SimpleNote)

    @Update(entity = SimpleNote::class)
    suspend fun updateSimpleNote(simpleNote: SimpleNote)

    @Delete(entity = SimpleNote::class)
    suspend fun deleteTodoTask(simpleNote: SimpleNote)
}