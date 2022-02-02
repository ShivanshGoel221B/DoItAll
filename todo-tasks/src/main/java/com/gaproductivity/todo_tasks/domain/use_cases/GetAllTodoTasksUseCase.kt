package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
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