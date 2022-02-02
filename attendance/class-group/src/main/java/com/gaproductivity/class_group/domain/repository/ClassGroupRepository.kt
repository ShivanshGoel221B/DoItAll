package com.gaproductivity.class_group.domain.repository

import com.gaproductivity.database.entity.ClassGroup
import kotlinx.coroutines.flow.Flow

interface ClassGroupRepository {
    fun getAllClassGroups(): Flow<List<ClassGroup>>
    suspend fun getClassGroup(name: String): ClassGroup?
    suspend fun createClassGroup(classGroup: ClassGroup)
}