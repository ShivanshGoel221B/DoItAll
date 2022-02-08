package com.gaproductivity.todo_tasks.domain.use_cases

import com.gaproductivity.components.presentation.utils.Reminder
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import javax.inject.Inject

class CreateTodoTaskUseCase
@Inject constructor(
    private val repository: TodoTaskRepository
){
    @Inject
    lateinit var reminder: Reminder

    suspend operator fun invoke(todoTask: TodoTask) {
        todoTask.reminder?.let {
            reminder.createReminder(it, todoTask.todoTaskTitle)
        }
        repository.createTodoTask(todoTask)
    }
}