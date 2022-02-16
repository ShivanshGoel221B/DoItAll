package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.orange
import com.gaproductivity.components.presentation.theme.primaryColor
import com.gaproductivity.core.domain.Converters
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.isMissed
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent

@Composable
fun TodTaskCard(
    modifier: Modifier = Modifier,
    todoNavigation: (TodoNavigation) -> Unit,
    todoTaskEvent: (TodoTaskEvent) -> Unit,
    todoTask: TodoTask
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.cardColor
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .clickable { todoNavigation(TodoNavigation.ToTodoTask(todoTask.todoTaskId!!)) },
                    text = todoTask.todoTaskTitle,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp
                        else Icons.Default.ArrowDropDown,
                        contentDescription = "Expand/Collapse Button"
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

            AnimatedVisibility(visible = expanded) {

                Column {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .clickable { todoNavigation(TodoNavigation.ToTodoTask(todoTask.todoTaskId!!)) },
                        text = todoTask.todoTaskDescription
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TodoTasksListOptions(
                        onDeleteClick = {
                            todoTaskEvent(
                                TodoTaskEvent.DeleteTodoTaskClicked(todoTask)
                            )
                        },
                        onEditClick = {
                            todoNavigation(
                                TodoNavigation.ToEditTodoTask(
                                    todoTask = todoTask,
                                    todoTaskGroupId = todoTask.todoTaskGroupId
                                )
                            )
                        },
                        onMarkClick = {
                            todoTaskEvent(
                                TodoTaskEvent.MarkTodoTask(todoTask, !todoTask.isComplete)
                            )
                        },
                        isComplete = todoTask.isComplete
                    )

                    Divider()
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    if (todoTask.deadline == null)
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = "No Deadline",
                            fontSize = 14.sp
                        )
                    else
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = "Due: ${
                                Converters
                                    .getFormattedDate(todoTask.deadline!!)
                            } (${
                                Converters
                                    .getFormattedTime(todoTask.deadline!!)
                            })",
                            fontSize = 14.sp,
                            color = when {
                                todoTask.isMissed() -> Color.Red
                                todoTask.isComplete -> Color.Green
                                else -> primaryColor
                            }
                        )

                    if (todoTask.doneAt !== null)
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            text = "Done At: ${
                                Converters
                                    .getFormattedDate(todoTask.doneAt!!)
                            } (${
                                Converters
                                    .getFormattedTime(todoTask.doneAt!!)
                            })",
                            fontSize = 14.sp,
                            color = when {
                                todoTask.isMissed() -> orange
                                else -> primaryColor
                            }
                        )
                }

                val notificationIcon = if (todoTask.reminder == null)
                    Icons.Default.NotificationsOff else Icons.Default.NotificationsActive

                Icon(
                    imageVector = notificationIcon,
                    contentDescription = "Reminder",
                    modifier = Modifier.size(26.dp)
                )

            }
        }
    }
}