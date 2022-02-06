package com.gaproductivity.doitall.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.DoItAllTheme
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.doitall.presentation.components.destinations.AddTodoTaskGroupNavDestination
import com.gaproductivity.doitall.presentation.components.destinations.EditTodoTaskGroupNavDestination
import com.gaproductivity.doitall.presentation.screens.HomeScreen
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel
import com.gaproductivity.todo_tasks.presentation.ui.AddEditTodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.ui.TodoTasksGroupsListScreen
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true, style = DefaultNavAnimation::class)
fun HomeScreenNav(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    DoItAllTheme(darkTheme = mainViewModel.darkMode.value) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.surface
        )
        Scaffold{
            HomeScreen(
                navigator = navigator,
                todoNavigation = { navigation ->
                    navigateTodoTask(navigator = navigator, todoNavigation = navigation)
                }
            )
        }
    }
}

@Destination(style = DefaultNavAnimation::class)
@Composable
fun TodoTasksGroupsScreenNav(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    DoItAllTheme(darkTheme = mainViewModel.darkMode.value) {
        TodoTasksGroupsListScreen(
            navigator = navigator,
            titleBar = {
                TopBar(screenTitle = "Task Groups")
            },
            todoNavigation = {
                navigateTodoTask(navigator, it)
            }
        )
    }
}

@Destination(style = DefaultNavAnimation::class)
@Composable
fun AddTodoTaskGroupNav(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    DoItAllTheme(darkTheme = mainViewModel.darkMode.value) {
        AddEditTodoTaskGroup(
            navigator = navigator,
            titleBar = {
                TopBar(screenTitle = "Add Todo Group")
            })
    }
}

@Destination(style = DefaultNavAnimation::class)
@Composable
fun EditTodoTaskGroupNav(
    navigator: DestinationsNavigator,
    todoTaskGroup: TodoTaskGroup,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    DoItAllTheme(darkTheme = mainViewModel.darkMode.value) {
        AddEditTodoTaskGroup(
            navigator = navigator,
            initialTodoTaskGroup = todoTaskGroup,
            titleBar = {
                TopBar(screenTitle = "Edit Task Group")
            }
        )
    }
}


fun navigateTodoTask(
    navigator: DestinationsNavigator,
    todoNavigation: TodoNavigation
) {
    when (todoNavigation) {
        is TodoNavigation.ToAddNewTodoTaskGroup -> {
            navigator.navigate(direction = AddTodoTaskGroupNavDestination)
        }
        is TodoNavigation.ToEditTodoTaskGroup -> {
            navigator.navigate(
                EditTodoTaskGroupNavDestination(
                    todoTaskGroup = todoNavigation.todoTaskGroup
                )
            )
        }
        is TodoNavigation.ToTodoTask -> {

        }
        else -> Unit
    }
}