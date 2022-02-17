package com.gaproductivity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaproductivity.database.dao.ClassGroupDao
import com.gaproductivity.database.dao.NoteBookDao
import com.gaproductivity.database.dao.SimpleNoteDao
import com.gaproductivity.database.dao.TodoTaskDao
import com.gaproductivity.database.entity.*

@Database(
    entities = [
        ClassGroup::class,
        TodoTaskGroup::class,
        TodoTask::class,
        NoteBook::class,
        SimpleNote::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val classGroupDao: ClassGroupDao
    abstract val todoTaskDao: TodoTaskDao
    abstract val noteBookDao: NoteBookDao
    abstract val simpleNoteDao: SimpleNoteDao
}