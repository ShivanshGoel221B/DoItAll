package com.gaproductivity.doitall.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.components.presentation.theme.translucentGray
import com.gaproductivity.todo_tasks.presentation.ui.components.HorizontalUpcomingTaskRow
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel

@Composable
fun UpcomingTasks(
    modifier: Modifier = Modifier,
    viewModel: TodoTaskViewModel = hiltViewModel(),
    todoNavigation: (TodoNavigation) -> Unit
) {
    val upcomingTodoTasks = viewModel.getUpcomingTasks(viewModel.allTodoTasks.value)

    Column(
        modifier = modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        Text(
            text = "Upcoming Tasks",
            color = MaterialTheme.colors.textColor,
            modifier = Modifier.padding(horizontal = 14.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Card(
            border = BorderStroke(0.5.dp, MaterialTheme.colors.translucentGray),
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 0.dp
        ) {
            if (upcomingTodoTasks.isEmpty())
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp), contentAlignment = Alignment.Center) {
                    Text(text = "No Upcoming Tasks", color = MaterialTheme.colors.textColor)
                }
            else
                HorizontalUpcomingTaskRow(upcomingTasks = upcomingTodoTasks, todoNavigation = todoNavigation)
        }
    }
}