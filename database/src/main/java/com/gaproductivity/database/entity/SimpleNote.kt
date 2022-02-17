package com.gaproductivity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaproductivity.database.Constants
import java.io.Serializable

@Entity(tableName = Constants.SIMPLE_NOTES)
data class SimpleNote(

    @ColumnInfo(name = Constants.SIMPLE_NOTE_ID)
    @PrimaryKey(autoGenerate = true)
    val simpleNoteId: Int? = null,

    @ColumnInfo(name = Constants.NOTE_BOOK_ID)
    val noteBookId: Int,

    @ColumnInfo(name = Constants.SIMPLE_NOTE_TITLE)
    val noteTitle: String,

    @ColumnInfo(name = Constants.SIMPLE_NOTE_CONTENT)
    val noteContent: String,

    @ColumnInfo(name = Constants.IS_SYNCED)
    var isSynced: Boolean = false,

    @ColumnInfo(name = Constants.CREATED_AT)
    val createdAt: Long
): Serializable
