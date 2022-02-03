package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import javax.inject.Inject

class CreateTodoTaskGroupUseCase @Inject constructor(
    private val repository: TodoTaskRepository
) {
    suspend operator fun invoke(todoTaskGroup: TodoTaskGroup) {
        todoTaskGroup.todoTaskGroupName = todoTaskGroup.todoTaskGroupName.trim()
        repository.createTodoGroup(todoTaskGroup)
    }
}