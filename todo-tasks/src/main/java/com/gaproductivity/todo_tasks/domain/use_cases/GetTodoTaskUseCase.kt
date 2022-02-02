package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
import javax.inject.Inject

class GetTodoTaskUseCase
@Inject constructor(
    private val repository: TodoTaskRepository
){
    suspend operator fun invoke(todoTaskId: Int): TodoTask? {
        return repository.getTodoTaskById(todoTaskId)
    }
}