package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.ui.DialogBox
import com.gaproductivity.core.domain.DialogModel
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TodoTasksListScreen(
    modifier: Modifier = Modifier,
    todoTaskGroup: TodoTaskGroup,
    navigator: DestinationsNavigator,
    titleBar: @Composable () -> Unit,
    todoNavigation: (TodoNavigation) -> Unit,
    todoTaskViewModel: TodoTaskViewModel = hiltViewModel()
) {

    val allTodoTasks by remember {
        todoTaskViewModel.allTodoTasks
    }

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        todoTaskViewModel.uiEvents.collect { event: UiEvents ->
            when (event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvents.PopBackStack -> {
                    navigator.popBackStack()
                }
                else -> Unit
            }
        }
    }

    AnimatedVisibility(todoTaskViewModel.showDeleteDialog.value) {
        DialogBox(dialogModel = DialogModel(
            title = "Delete Confirmation",
            message = "Are you sure you want to delete this Todo Task?",
            onDismiss = {
                todoTaskViewModel.onEvent(TodoTaskEvent.DismissTodoTaskDelete)
            },
            onPositive = {
                todoTaskViewModel.onEvent(TodoTaskEvent.ConfirmTodoTaskDelete)
            },
            onNegative = {
                todoTaskViewModel.onEvent(TodoTaskEvent.DismissTodoTaskDelete)
            }
        ))
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    todoNavigation(TodoNavigation.ToAddNewTodoTask)
                }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 14.dp),
                    text = "Add New Task",
                    color = Color.White
                )
            }
        },
        topBar = titleBar
    ) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = 2,
            state = pagerState
        ) {
            Text(text = todoTaskGroup.todoTaskGroupName)
        }
    }
}