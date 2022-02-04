package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodoTaskGroupsUseCase @Inject constructor(
    private val repository: TodoTaskRepository
) {
    operator fun invoke(): Flow<List<TodoTaskGroup>> {
        return repository.getAllTodoTaskGroups()
    }
}