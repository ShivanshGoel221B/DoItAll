package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel

@Composable
fun HorizontalPendingTodoTaskRow(
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
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (pendingGroupTasks.isNotEmpty()) {
            Text(text = "Pending Tasks:", modifier = Modifier.padding(horizontal = 6.dp))
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                modifier = Modifier
                    .padding(6.dp)
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                pendingGroupTasks.forEach { todoTask ->
                    item {
                        Spacer(modifier = Modifier.width(6.dp))
                        Card(
                            elevation = 6.dp,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .clickable {
                                    todoNavigation(
                                        TodoNavigation.ToEditTodoTask(
                                            todoTask,
                                            todoTask.todoTaskGroupId
                                        )
                                    )
                                }
                                .width(120.dp),
                            backgroundColor = MaterialTheme.colors.cardColor
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(), contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = todoTask.todoTaskTitle,
                                    color = MaterialTheme.colors.textColor,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
            }
        }
        else
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "There are no Pending Tasks in this group",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
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
                    fontSize = 14.sp
                )
            }
        }
    }
}