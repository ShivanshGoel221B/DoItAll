package com.gaproductivity.todo_tasks.presentation.ui.components

import com.gaproductivity.database.entity.TodoTaskGroup

sealed class TodoNavigation {
    object ToAddNewTodoTaskGroup: TodoNavigation()
    data class ToEditTodoTaskGroup(val todoTaskGroup: TodoTaskGroup): TodoNavigation()
    data class ToTodoTaskGroup(val groupId: Int): TodoNavigation()
}
