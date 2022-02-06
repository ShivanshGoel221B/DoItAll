package com.gaproductivity.todo_tasks.presentation.ui.components

import com.gaproductivity.database.entity.TodoTaskGroup

sealed class TodoNavigation {
    object ToAddNewTodoTaskGroup: TodoNavigation()
    object ToAddNewTodoTask: TodoNavigation()
    data class ToEditTodoTaskGroup(val todoTaskGroup: TodoTaskGroup): TodoNavigation()
    data class ToTodoTasksList(val todoTaskGroup: TodoTaskGroup): TodoNavigation()
    data class ToTodoTask(val todoTaskId: Int): TodoNavigation()
}
