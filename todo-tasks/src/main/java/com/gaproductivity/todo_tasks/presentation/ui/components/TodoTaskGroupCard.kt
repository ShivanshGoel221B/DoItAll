package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent


@Composable
fun TodoTaskGroupCard(
    modifier: Modifier = Modifier,
    todoTaskGroup: TodoTaskGroup,
    onEvent: (TodoTaskEvent) -> Unit,
    todoNavigation: (TodoNavigation) -> Unit
) {

    var viewOptions by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.cardColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.cardColor),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todoTaskGroup.todoTaskGroupName,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    ),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.textColor,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = {
                    viewOptions = !viewOptions
                }) {
                    val icon =
                        if (viewOptions) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
                    Icon(
                        imageVector = icon,
                        contentDescription = "Show More",
                        tint = MaterialTheme.colors.textColor
                    )
                }
            }
            AnimatedVisibility(visible = viewOptions) {
                Column {
                    TodoCardOptions(
                        onDeleteClick = {
                            onEvent(
                                TodoTaskEvent.DeleteTodoTaskGroupClicked(todoTaskGroup)
                            )
                        },
                        onEditClick = {
                            todoNavigation(
                                TodoNavigation.ToEditTodoTaskGroup(todoTaskGroup)
                            )
                        },
                        onMarkClick = {
                            onEvent(TodoTaskEvent.MarkAllAsDone(todoTaskGroup.todoTaskGroupId))
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colors.cardColor)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp
                    ).heightIn(100.dp, 180.dp)
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalPendingTodoTaskRow(
                    todoNavigation = todoNavigation,
                    todoTaskGroup = todoTaskGroup
                )
            }
        }
    }
}