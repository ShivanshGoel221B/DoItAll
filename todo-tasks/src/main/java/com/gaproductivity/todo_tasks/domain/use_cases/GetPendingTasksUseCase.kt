package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.database.entity.TodoTask
import javax.inject.Inject

class GetPendingTasksUseCase @Inject constructor() {

    operator fun invoke(todoTasksList: List<TodoTask>): List<TodoTask> {
        return todoTasksList.filter {
            !it.isComplete
        }
    }
}