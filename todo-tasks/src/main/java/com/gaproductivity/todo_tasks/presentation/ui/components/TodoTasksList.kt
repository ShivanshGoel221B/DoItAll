package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel

@Composable
fun TodoTasksList(
    modifier: Modifier = Modifier,
    fullTodoTasksList: List<TodoTask>,
    todoTaskGroupId: Int,
    isPending: Boolean = true,
    todoTaskViewModel: TodoTaskViewModel = hiltViewModel()
) {
    val filteredList by remember {
        mutableStateOf(
            if (isPending)
                todoTaskViewModel.getPendingGroupTodoTasks(fullTodoTasksList, todoTaskGroupId)
            else
                todoTaskViewModel.getPendingGroupTodoTasks(fullTodoTasksList, todoTaskGroupId)
        )
    }
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp),
        contentPadding = PaddingValues(bottom = 110.dp)
    ) {
        for (todoTask in fullTodoTasksList) {

        }
    }
}