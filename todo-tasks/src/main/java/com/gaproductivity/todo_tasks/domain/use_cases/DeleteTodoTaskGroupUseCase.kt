package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import javax.inject.Inject

class DeleteTodoTaskGroupUseCase @Inject constructor(
    private val todoTaskRepository: TodoTaskRepository
) {
    suspend operator fun invoke(todoTaskGroup: TodoTaskGroup) {
        todoTaskRepository.deleteTodoTaskGroup(todoTaskGroup)
    }
}