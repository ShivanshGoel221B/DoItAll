package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import javax.inject.Inject

class CreateTodoTaskUseCase
@Inject constructor(
    private val repository: TodoTaskRepository
){
    suspend operator fun invoke(todoTask: TodoTask) {
        repository.createTodoTask(todoTask)
    }
}