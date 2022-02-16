package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.primaryColor
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel

@Composable
fun TodoTasksList(
    modifier: Modifier = Modifier,
    fullTodoTasksList: List<TodoTask>,
    todoTaskGroupId: Int,
    todoNavigation: (TodoNavigation) -> Unit,
    isPending: Boolean = true,
    isMissed: Boolean = false,
    todoTaskViewModel: TodoTaskViewModel = hiltViewModel()
) {
    var filteredList by remember {
        mutableStateOf(fullTodoTasksList)
    }

    filteredList =
        when {
            isMissed -> todoTaskViewModel.getMissedGroupTodoTasks(
                fullTodoTasksList,
                todoTaskGroupId
            )
            isPending -> todoTaskViewModel.getPendingGroupTodoTasks(
                fullTodoTasksList,
                todoTaskGroupId
            )
            else -> todoTaskViewModel.getCompletedGroupTodoTasks(fullTodoTasksList, todoTaskGroupId)
        }
    if (filteredList.isEmpty())
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No Tasks Found", fontSize = 18.sp, color = primaryColor)
        }
    else
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(top = 4.dp),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            for (todoTask in filteredList) {
                item {
                    Spacer(modifier = Modifier.height(6.dp))
                    TodTaskCard(
                        todoNavigation = todoNavigation,
                        todoTask = todoTask,
                        todoTaskEvent = todoTaskViewModel::onEvent
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }
        }
}