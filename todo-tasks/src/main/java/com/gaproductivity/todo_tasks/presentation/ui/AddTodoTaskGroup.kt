package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun AddTodoTaskGroup(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    titleBar: @Composable ()-> Unit,
    todoNavigation: (TodoNavigation) -> Unit,
    viewModel: TodoTaskViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = titleBar
    ) {
        val todoTaskGroup: TodoTaskGroup = viewModel.createTodoGroup.value
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Enter the Group Name",
                fontSize = 22.sp,

            )
            Spacer(modifier = Modifier.size(6.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = todoTaskGroup.todoTaskGroupName,
                onValueChange = viewModel::updateCreateGroup,

                placeholder = {
                    Text("Enter Group Name")
                }
            )
        }
    }

}