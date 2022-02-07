package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditTodoTask(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    initialTodoTask: TodoTask? = null,
    todoTaskGroupId: Int,
    viewModel: TodoTaskViewModel = hiltViewModel(),
    titleBar: @Composable () -> Unit
) {
    val onEvent = viewModel::onEvent
    LaunchedEffect(key1 = true) {
        viewModel.initAddEditTodoTask(
            initialTodoTask,
            todoTaskGroupId
        )
        viewModel.uiEvents.collect {event ->
            if (event is UiEvents.PopBackStack)
                navigator.popBackStack()
        }
    }

    val todoTask = viewModel.addEditTodoTask.value

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = titleBar,
        floatingActionButton = {
            Button(
                onClick = {
                    onEvent(TodoTaskEvent.SubmitTodoTaskGroup)
                },
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Create",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                )
            }
        }
    ) {

    }
}