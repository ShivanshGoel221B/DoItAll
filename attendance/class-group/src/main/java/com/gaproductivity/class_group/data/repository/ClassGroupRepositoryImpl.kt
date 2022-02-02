package com.gaproductivity.class_group.data.repository

import com.gaproductivity.class_group.domain.repository.ClassGroupRepository
import com.gaproductivity.database.dao.ClassGroupDao
import com.gaproductivity.database.entity.ClassGroup
import kotlinx.coroutines.flow.Flow

class ClassGroupRepositoryImpl(private val dao: ClassGroupDao) : ClassGroupRepository {

    override fun getAllClassGroups(): Flow<List<ClassGroup>> {
        return dao.getAllClassGroups()
    }

    override suspend fun getClassGroup(name: String): ClassGroup? {
        return dao.getClassGroup(name)
    }

    override suspend fun createClassGroup(classGroup: ClassGroup) {
        dao.createClassGroup(classGroup)
    }
}