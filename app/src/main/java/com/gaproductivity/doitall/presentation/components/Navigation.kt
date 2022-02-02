package com.gaproductivity.doitall.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.doitall.presentation.ui.theme.DoItAllTheme
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel
import com.gaproductivity.todo_tasks.presentation.ui.TodoTasksListScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = DefaultNavAnimation::class)
@Composable
fun TodoTasksScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    DoItAllTheme(darkTheme = mainViewModel.darkMode.value) {
        TodoTasksListScreen(
            navigator = navigator,
            titleBar = {
                TopBar(screenTitle = "Task Groups")
            }
        )
    }
}