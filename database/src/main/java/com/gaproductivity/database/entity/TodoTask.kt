package com.gaproductivity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaproductivity.database.Constants

@Entity(tableName = Constants.TODO_TASK)
data class TodoTask(

    @PrimaryKey(autoGenerate = true)
    val todoTaskId: Int? = null,

    @ColumnInfo(name = Constants.TODO_TASK_TITLE)
    val todoTaskTitle: String,

    @ColumnInfo(name = Constants.TODO_TASK_GROUP_ID)
    val todoTaskGroupId: Int,

    @ColumnInfo(name = Constants.TODO_TASK_DESCRIPTION)
    val todoTaskDescription: String,

    @ColumnInfo(name = Constants.TODO_TASK_COLOR)
    val todoTaskColor: Int,

    @ColumnInfo(name = Constants.IS_COMPLETE)
    val isComplete: Boolean = false,

    @ColumnInfo(name = Constants.REMINDER)
    val reminder: Long? = null,

    @ColumnInfo(name = Constants.IS_SYNCED)
    val isSynced: Boolean = false
)