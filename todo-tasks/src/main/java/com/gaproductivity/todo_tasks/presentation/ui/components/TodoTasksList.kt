package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    isMissed: Boolean = false,
    todoTaskViewModel: TodoTaskViewModel = hiltViewModel()
) {
    var filteredList by remember {
        mutableStateOf(fullTodoTasksList)
    }

    filteredList =
        when {
            isMissed -> todoTaskViewModel.getMissedGroupTodoTasks(fullTodoTasksList, todoTaskGroupId)
            isPending -> todoTaskViewModel.getPendingGroupTodoTasks(fullTodoTasksList, todoTaskGroupId)
            else -> todoTaskViewModel.getCompletedGroupTodoTasks(fullTodoTasksList, todoTaskGroupId)
        }
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp),
        contentPadding = PaddingValues(bottom = 110.dp)
    ) {
        for (todoTask in filteredList) {
            item { 
                Card(backgroundColor = Color(todoTask.todoTaskColor), modifier = Modifier.padding(20.dp)) {
                    Text(text = todoTask.todoTaskTitle)
                }
            }
        }
    }
}