package com.gaproductivity.class_group.domain.use_cases

import com.gaproductivity.class_group.domain.repository.ClassGroupRepository
import com.gaproductivity.database.entity.ClassGroup
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGroupsUseCase
@Inject constructor(
    private val repository: ClassGroupRepository
) {
    operator fun invoke(): Flow<List<ClassGroup>> {
        return repository.getAllClassGroups()
    }
}