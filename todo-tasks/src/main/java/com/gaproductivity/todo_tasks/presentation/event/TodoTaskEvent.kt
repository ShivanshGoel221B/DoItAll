package com.gaproductivity.todo_tasks.presentation.event

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup

sealed class TodoTaskEvent {
    object SubmitTodoTaskGroup: TodoTaskEvent()
    object SubmitTodoTask: TodoTaskEvent()
    data class DeleteTodoTaskGroupClicked(val todoTaskGroup: TodoTaskGroup): TodoTaskEvent()
    data class DeleteTodoTaskClicked(val todoTask: TodoTask): TodoTaskEvent()
    data class ItemDeleted(val itemName: String): TodoTaskEvent()
    object ConfirmTodoTaskDelete: TodoTaskEvent()
    object ConfirmTodoTaskGroupDelete: TodoTaskEvent()
    object DismissTodoTaskDelete: TodoTaskEvent()
    object DismissTodoTaskGroupDelete: TodoTaskEvent()
    data class MarkAllAsDone(val todoTaskGroupId: Int?): TodoTaskEvent()
    data class MarkTodoTask(val todoTask: TodoTask, val isComplete: Boolean = true): TodoTaskEvent()
}
