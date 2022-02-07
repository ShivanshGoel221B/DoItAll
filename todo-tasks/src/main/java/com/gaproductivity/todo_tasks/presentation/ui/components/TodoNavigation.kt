package com.gaproductivity.todo_tasks.presentation.ui.components

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup

sealed class TodoNavigation {
    object ToAddNewTodoTaskGroup: TodoNavigation()
    data class ToAddNewTodoTask(val todoTaskGroupId: Int): TodoNavigation()
    data class ToEditTodoTaskGroup(val todoTaskGroup: TodoTaskGroup): TodoNavigation()
    data class ToEditTodoTask(val todoTask: TodoTask, val todoTaskGroupId: Int): TodoNavigation()
    data class ToTodoTasksList(val todoTaskGroup: TodoTaskGroup): TodoNavigation()
    data class ToTodoTask(val todoTaskId: Int): TodoNavigation()
}
