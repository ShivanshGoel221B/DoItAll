package com.gaproductivity.class_group.domain.use_cases

import com.gaproductivity.class_group.domain.repository.ClassGroupRepository
import com.gaproductivity.database.entity.ClassGroup
import javax.inject.Inject

class CreateClassGroupUseCase
@Inject constructor(
    private val repository: ClassGroupRepository
){
    suspend operator fun invoke(classGroup: ClassGroup) {
        repository.createClassGroup(classGroup = classGroup)
    }
}