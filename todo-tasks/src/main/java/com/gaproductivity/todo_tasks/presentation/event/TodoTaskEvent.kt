package com.gaproductivity.todo_tasks.presentation.event

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup

sealed class TodoTaskEvent {
    data class TodoTaskGroupClicked(val groupId: Int): TodoTaskEvent()
    data class TodoTaskClicked(val todoTaskId: Int): TodoTaskEvent()
    object SubmitNewTodoTaskGroup: TodoTaskEvent()
    object SubmitNewTodoTask: TodoTaskEvent()
    data class EditTodoTaskGroupClicked(val todoTaskGroup: TodoTaskGroup): TodoTaskEvent()
    data class EditTodoTaskClicked(val todoTask: TodoTask): TodoTaskEvent()
    data class DeleteTodoTaskGroupClicked(val todoTaskGroup: TodoTaskGroup): TodoTaskEvent()
    data class DeleteTodoTaskClicked(val todoTask: TodoTask): TodoTaskEvent()
    data class ItemDeleted(val itemName: String): TodoTaskEvent()
    object ConfirmTodoTaskDelete: TodoTaskEvent()
    object ConfirmTodoTaskGroupDelete: TodoTaskEvent()
    object DismissTodoTaskDelete: TodoTaskEvent()
    object DismissTodoTaskGroupDelete: TodoTaskEvent()
}
