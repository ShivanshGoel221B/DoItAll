package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.ui.DialogBox
import com.gaproductivity.core.domain.DialogModel
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoTaskGroupCard
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
fun TodoTasksGroupsListScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    titleBar: @Composable () -> Unit,
    todoNavigation: (TodoNavigation) -> Unit,
    todoTaskViewModel: TodoTaskViewModel = hiltViewModel()
) {
    val groupsList = todoTaskViewModel.allTodoTaskGroups

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        todoTaskViewModel.uiEvents.collect { event: UiEvents ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                else -> Unit
            }
        }
    }

    AnimatedVisibility(todoTaskViewModel.showDeleteDialog.value) {
        DialogBox(dialogModel = DialogModel(
            title = "Delete Confirmation",
            message = "Deleting this group will also delete all Todo Tasks in this group. Are you sure you want to continue?",
            onDismiss = {
                todoTaskViewModel.onEvent(TodoTaskEvent.DismissTodoTaskGroupDelete)
            },
            onPositive = {
                todoTaskViewModel.onEvent(TodoTaskEvent.ConfirmTodoTaskGroupDelete)
            },
            onNegative = {
                todoTaskViewModel.onEvent(TodoTaskEvent.DismissTodoTaskGroupDelete)
            }
        ))
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    todoNavigation(TodoNavigation.ToAddNewTodoTaskGroup)
                }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 14.dp),
                    text = "Add New Tasks Group",
                    color = Color.White
                )
            }
        },
        topBar = titleBar
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            groupsList.value.forEach { todoTaskGroup ->
                item {
                    Spacer(modifier = Modifier.size(24.dp))
                    TodoTaskGroupCard(
                        todoTaskGroup = todoTaskGroup,
                        navigator = navigator,
                        onEvent = todoTaskViewModel::onEvent
                    )
                }
            }
        }

    }
}