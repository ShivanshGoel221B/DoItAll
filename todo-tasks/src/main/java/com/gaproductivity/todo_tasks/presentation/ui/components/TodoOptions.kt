package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent

@Composable
fun TodoTasksListOptions(
    modifier: Modifier = Modifier,
    model: Any,
    onEvent: (TodoTaskEvent) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
              if (model is TodoTaskGroup) {
                  onEvent(TodoTaskEvent.DeleteTodoTaskGroupClicked(model))
              } else if (model is TodoTask) {
                  onEvent(TodoTaskEvent.DeleteTodoTaskClicked(model))
              }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = Icons.Default.DeleteForever, contentDescription = "Delete")
        }

        IconButton(
            onClick = {
                if (model is TodoTaskGroup) {
                    onEvent(TodoTaskEvent.EditTodoTaskGroupClicked(model))
                } else if (model is TodoTask) {
                    onEvent(TodoTaskEvent.EditTodoTaskClicked(model))
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
        }

    }
}