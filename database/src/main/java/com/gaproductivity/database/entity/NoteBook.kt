package com.gaproductivity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaproductivity.database.Constants
import java.io.Serializable

@Entity(tableName = Constants.NOTE_BOOK)
data class NoteBook(

    @ColumnInfo(name = Constants.NOTE_BOOK_ID)
    @PrimaryKey(autoGenerate = true)
    val noteBookId: Int? = null,

    @ColumnInfo(name = Constants.NOTE_BOOK_NAME)
    val noteBookName: String,

    @ColumnInfo(name = Constants.IS_SYNCED)
    val isSynced: Boolean = false

): Serializable
