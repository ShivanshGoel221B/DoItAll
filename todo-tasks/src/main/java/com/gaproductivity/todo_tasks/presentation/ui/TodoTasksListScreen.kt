package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentLate
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.orange
import com.gaproductivity.components.presentation.ui.DialogBox
import com.gaproductivity.core.domain.DialogModel
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoTasksList
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


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

    todoTaskViewModel.getTodoTasksByGroupId(todoTaskGroup.todoTaskGroupId!!)

    val pagerState = rememberPagerState(pageCount = 3)

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
        val tabIndex = pagerState.currentPage
        val coroutineScope = rememberCoroutineScope()

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PendingRadioButton(
                    isSelected = tabIndex == 0,
                    onClick = {
                        if (tabIndex != 0)
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                    }
                )

                CompletedRadioButton(
                    isSelected = tabIndex == 1,
                    onClick = {
                        if (tabIndex != 1)
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                    }
                )

                MissedRadioButton(
                    isSelected = tabIndex == 2,
                    onClick = {
                        if (tabIndex != 2)
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                    }
                )
            }

            HorizontalPager(
                state = pagerState,
            ) { index ->
                when (index) {
                    0 -> TodoTasksList(
                        modifier = Modifier.fillMaxSize(),
                        fullTodoTasksList = allTodoTasks,
                        todoTaskGroupId = todoTaskGroup.todoTaskGroupId!!
                    )
                    1 -> TodoTasksList(
                        modifier = Modifier.fillMaxSize(),
                        fullTodoTasksList = allTodoTasks,
                        todoTaskGroupId = todoTaskGroup.todoTaskGroupId!!,
                        isPending = false
                    )
                    else -> TodoTasksList(
                        modifier = Modifier.fillMaxSize(),
                        fullTodoTasksList = allTodoTasks,
                        todoTaskGroupId = todoTaskGroup.todoTaskGroupId!!,
                        isPending = false,
                        isMissed = true
                    )
                }
            }
        }
    }
}

@Composable
fun PendingRadioButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) orange else MaterialTheme.colors.background,
            contentColor = if (isSelected) Color.White else orange
        ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(
            width = 2.dp,
            color = orange
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PendingActions,
                contentDescription = "Pending Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Pending",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CompletedRadioButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Green else MaterialTheme.colors.background,
            contentColor = if (isSelected) Color.White else Color.Green
        ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Green
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DoneAll,
                contentDescription = "Completed Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Done",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun MissedRadioButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Red else MaterialTheme.colors.background,
            contentColor = if (isSelected) Color.White else Color.Red
        ),
        shape = RoundedCornerShape(25.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Red
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AssignmentLate,
                contentDescription = "Missed Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Missed",
                fontSize = 14.sp
            )
        }
    }
}