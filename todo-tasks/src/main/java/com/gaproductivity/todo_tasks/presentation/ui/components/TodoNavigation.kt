package com.gaproductivity.todo_tasks.presentation.ui.components

sealed class TodoNavigation {
    object ToAddNewTodoTaskGroup: TodoNavigation()
    data class ToTodoTaskGroup(val groupId: Int): TodoNavigation()
}
