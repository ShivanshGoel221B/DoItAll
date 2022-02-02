package com.gaproductivity.todo_tasks.presentation.event

sealed class TodoTaskListEvent {
    data class TodoTaskClicked(val todoTaskId: Int): TodoTaskListEvent()
}
