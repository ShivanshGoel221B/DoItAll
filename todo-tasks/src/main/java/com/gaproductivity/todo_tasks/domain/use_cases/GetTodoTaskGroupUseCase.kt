package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import javax.inject.Inject

class GetTodoTaskGroupUseCase
@Inject constructor(
    private val repository: TodoTaskRepository
){
    suspend operator fun invoke(todoTaskGroupId: Int): TodoTaskGroup? {
        return repository.getTodoTaskGroupById(todoTaskGroupId)
    }
}