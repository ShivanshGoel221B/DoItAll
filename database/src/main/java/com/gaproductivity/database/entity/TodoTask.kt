package com.gaproductivity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaproductivity.database.Constants
import java.io.Serializable
import java.util.*

@Entity(tableName = Constants.TODO_TASK)
data class TodoTask(

    @PrimaryKey(autoGenerate = true)
    val todoTaskId: Int? = null,

    @ColumnInfo(name = Constants.TODO_TASK_TITLE)
    var todoTaskTitle: String,

    @ColumnInfo(name = Constants.TODO_TASK_GROUP_ID)
    val todoTaskGroupId: Int,

    @ColumnInfo(name = Constants.TODO_TASK_DESCRIPTION)
    var todoTaskDescription: String,

    @ColumnInfo(name = Constants.IS_COMPLETE)
    val isComplete: Boolean = false,

    @ColumnInfo(name = Constants.DEADLINE)
    val deadline: Long? = null,

    @ColumnInfo(name = Constants.REMINDER)
    val reminder: Long? = null,

    @ColumnInfo(name = Constants.CREATED_AT)
    val createdAt: Long,

    @ColumnInfo(name = Constants.DONE_AT)
    val doneAt: Long? = null,

    @ColumnInfo(name = Constants.IS_SYNCED)
    val isSynced: Boolean = false
): Serializable

fun TodoTask.isMissed(): Boolean {
    if (deadline == null)
        return false
    val now = Calendar.getInstance().timeInMillis
    if (now > deadline)
        return true
    return false
}