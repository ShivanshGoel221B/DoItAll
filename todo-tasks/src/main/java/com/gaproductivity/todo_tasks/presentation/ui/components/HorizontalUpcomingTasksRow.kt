package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.primaryColor
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.core.domain.Converters
import com.gaproductivity.database.entity.TodoTask

@Composable
fun HorizontalUpcomingTaskRow(
    upcomingTasks: List<TodoTask>,
    todoNavigation: (TodoNavigation) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(6.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        upcomingTasks.forEach { todoTask ->
            item {
                Spacer(modifier = Modifier.width(6.dp))

                Card(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.clickable {
                        todoNavigation(
                            TodoNavigation.ToEditTodoTask(todoTask, todoTask.todoTaskGroupId)
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = todoTask.todoTaskTitle,
                                color = MaterialTheme.colors.textColor,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 18.sp
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(horizontal = 4.dp).fillMaxWidth()) {
                                Text(
                                    text = "Due:",
                                    color = MaterialTheme.colors.textColor,
                                    fontSize = 12.sp
                                )
                                Divider()
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "${Converters.getFormattedDate(todoTask.deadline!!)}(${
                                        Converters.getFormattedTime(
                                            todoTask.deadline!!
                                        )
                                    })",
                                    color = MaterialTheme.colors.textColor,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}