package com.gaproductivity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaproductivity.database.dao.ClassGroupDao
import com.gaproductivity.database.dao.TodoTaskDao
import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup

@Database(
    entities = [ClassGroup::class, TodoTaskGroup::class, TodoTask::class],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract val classGroupDao: ClassGroupDao
    abstract val todoTaskDao: TodoTaskDao
}