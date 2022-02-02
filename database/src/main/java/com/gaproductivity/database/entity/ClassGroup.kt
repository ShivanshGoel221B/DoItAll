package com.gaproductivity.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaproductivity.database.Constants

@Entity(tableName = Constants.CLASS_GROUP)
data class ClassGroup(

    @PrimaryKey(autoGenerate = true)
    val classGroupId: Int? = null,

    @ColumnInfo(name = Constants.CLASS_GROUP_NAME)
    val classGroupName: String,

    @ColumnInfo(name = Constants.IS_SYNCED)
    val isSynced: Boolean = false
)
