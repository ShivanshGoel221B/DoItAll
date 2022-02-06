package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.subtitleColor
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditTodoTaskGroup(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    titleBar: @Composable () -> Unit,
    initialTodoTaskGroup: TodoTaskGroup? = null,
    viewModel: TodoTaskViewModel = hiltViewModel()
) {

    Scaffold(
        modifier = modifier,
        topBar = titleBar
    ) {
        val onEvent = viewModel::onEvent
        LaunchedEffect(key1 = true) {
            initialTodoTaskGroup?.let {
                viewModel.initUpdateTodoGroup(it)
            }
            viewModel.uiEvents.collect {event ->
                if (event is UiEvents.PopBackStack)
                    navigator.popBackStack()
            }
        }
        val todoTaskGroup: TodoTaskGroup = viewModel.createTodoGroup.value
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Enter the Group Name",
                fontSize = 16.sp,
                color = MaterialTheme.colors.textColor
            )
            Spacer(modifier = Modifier.size(6.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface),
                shape = MaterialTheme.shapes.medium,
                value = todoTaskGroup.todoTaskGroupName,
                onValueChange = viewModel::updateTodoGroupFormName,
                isError = viewModel.formNameError.value,
                textStyle = TextStyle(
                    color = MaterialTheme.colors.textColor,
                    fontSize = 18.sp
                ),
                placeholder = {
                    Text("Enter Group Name")
                }
            )
            if (viewModel.formNameError.value) {
                Text(
                    text = "Please Enter a valid name",
                    color = MaterialTheme.colors.error,
                    fontSize = 12.sp
                )
                Text(
                    text = "Name should not exceed 20 characters and should not contain any special character.",
                    color = subtitleColor,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Button(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 14.dp),
                onClick = {
                onEvent(TodoTaskEvent.SubmitTodoTaskGroup)
            }) {
                Text(
                    text = if(initialTodoTaskGroup == null) "Create" else "Update",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }

}