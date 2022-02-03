package com.gaproductivity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaproductivity.database.Constants

@Entity(tableName = Constants.TODO_TASK_GROUP)
data class TodoTaskGroup(

    @PrimaryKey(autoGenerate = true)
    val todoTaskGroupId: Int? = null,

    @ColumnInfo(name = Constants.TODO_TASK_GROUP_NAME)
    var todoTaskGroupName: String,

    @ColumnInfo(name = Constants.IS_SYNCED)
    val isSynced: Boolean = false
)