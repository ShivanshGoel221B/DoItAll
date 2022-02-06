package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel

@Composable
fun HorizontalTodoTaskRow(
    modifier: Modifier = Modifier,
    todoNavigation: (TodoNavigation) -> Unit,
    todoTaskGroup: TodoTaskGroup,
    todoTodoTaskViewModel: TodoTaskViewModel = hiltViewModel()
) {
    val todoTaskGroupId = todoTaskGroup.todoTaskGroupId!!
    val allTodoTasksList = todoTodoTaskViewModel.allTodoTasks.value
    val pendingGroupTasks = todoTodoTaskViewModel.getPendingGroupTodoTasks(
        allTodoTasksList,
        todoTaskGroupId
    )
    Column(
        modifier = modifier
            .padding(
                horizontal = 8.dp
            )
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (pendingGroupTasks.isNotEmpty())
            LazyRow {
                for (todoTask in pendingGroupTasks) {
                    item {
                        Spacer(modifier = Modifier.width(3.dp))



                        Spacer(modifier = Modifier.width(3.dp))
                    }
                }
            }
        else
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "There are no Pending Tasks in this group")
            }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 4.dp),
                onClick = {
                    todoNavigation(
                        TodoNavigation.ToTodoTasksList(todoTaskGroup)
                    )
                }
            ) {
                Text(
                    text = "View All",
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}