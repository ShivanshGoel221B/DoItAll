package com.gaproductivity.todo_tasks.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.gaproductivity.core.domain.UiEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
fun TodoTasksListScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: TodoTaskViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect { event: UiEvents ->
            when(event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = modifier.padding(16.dp).fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        FloatingActionButton(
            onClick = {

            }
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 14.dp),
                text = "Add New Group",
                color = Color.White
            )
        }

    }
}