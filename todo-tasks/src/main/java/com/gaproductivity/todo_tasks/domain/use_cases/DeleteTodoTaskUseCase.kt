package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import javax.inject.Inject

class DeleteTodoTaskUseCase @Inject constructor(
    private val todoTaskRepository: TodoTaskRepository
) {
    suspend operator fun invoke(todoTask: TodoTask) {
        todoTaskRepository.deleteTodoTask(todoTask)
    }
}