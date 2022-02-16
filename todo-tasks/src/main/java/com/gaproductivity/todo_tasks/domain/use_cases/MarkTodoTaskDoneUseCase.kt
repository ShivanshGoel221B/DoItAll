package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import java.util.*
import javax.inject.Inject

class MarkTodoTaskDoneUseCase @Inject constructor(
    private val repository: TodoTaskRepository
) {
    suspend operator fun invoke(todoTask: TodoTask, isComplete: Boolean = true) {
        repository.updateTodoTask(
            todoTask.copy(
                isComplete = isComplete,
                doneAt = Calendar.getInstance().timeInMillis
            )
        )
    }
}