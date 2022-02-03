package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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

    }

}