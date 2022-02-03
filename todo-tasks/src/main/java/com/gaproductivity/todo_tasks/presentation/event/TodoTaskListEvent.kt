package com.gaproductivity.todo_tasks.presentation.event

sealed class TodoTaskListEvent {
    data class TodoTaskGroupClicked(val groupId: Int): TodoTaskListEvent()
    data class TodoTaskClicked(val todoTaskId: Int): TodoTaskListEvent()
}
