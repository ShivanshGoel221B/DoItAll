package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodoTasksUseCase
@Inject constructor(
    private val repository: TodoTaskRepository
) {
    operator fun invoke(): Flow<List<TodoTask>> {
        return repository.getAllTodos()
    }
}