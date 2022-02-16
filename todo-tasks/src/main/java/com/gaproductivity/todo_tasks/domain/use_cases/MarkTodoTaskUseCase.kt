package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import java.util.*
import javax.inject.Inject

class MarkTodoTaskUseCase @Inject constructor(
    private val repository: TodoTaskRepository
) {
    suspend operator fun invoke(todoTask: TodoTask, isComplete: Boolean = true) {
        val doneAt = if (isComplete) Calendar.getInstance().timeInMillis else null
        repository.updateTodoTask(
            todoTask.copy(
                isComplete = isComplete,
                doneAt = doneAt
            )
        )
    }
}