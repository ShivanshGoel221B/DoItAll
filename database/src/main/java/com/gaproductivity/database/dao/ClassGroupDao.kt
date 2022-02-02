package com.gaproductivity.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.gaproductivity.database.Constants
import com.gaproductivity.database.Constants.CLASS_GROUP
import com.gaproductivity.database.entity.ClassGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassGroupDao {

    @Insert(onConflict = REPLACE)
    suspend fun createClassGroup(classGroup: ClassGroup)

    @Query("SELECT * FROM $CLASS_GROUP")
    fun getAllClassGroups(): Flow<List<ClassGroup>>

    @Query("SELECT * FROM $CLASS_GROUP WHERE ${Constants.CLASS_GROUP_NAME} = :name")
    suspend fun getClassGroup(name: String): ClassGroup?
}