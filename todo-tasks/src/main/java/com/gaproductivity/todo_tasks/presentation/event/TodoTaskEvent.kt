package com.gaproductivity.todo_tasks.presentation.event

sealed class TodoTaskEvent {
    data class TodoTaskGroupClicked(val groupId: Int): TodoTaskEvent()
    data class TodoTaskClicked(val todoTaskId: Int): TodoTaskEvent()
    object SubmitNewTodoTaskGroup: TodoTaskEvent()
    object SubmitNewTodoTask: TodoTaskEvent()
}
