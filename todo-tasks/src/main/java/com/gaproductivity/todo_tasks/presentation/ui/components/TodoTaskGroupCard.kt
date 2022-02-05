package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.gaproductivity.components.presentation.ui.DialogBox
import com.gaproductivity.core.domain.DialogModel
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun TodoTaskGroupCard(
    modifier: Modifier = Modifier,
    todoTaskGroup: TodoTaskGroup,
    navigator: DestinationsNavigator,
    onEvent: (TodoTaskEvent) -> Unit
) {

    val viewOptions: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        shape = MaterialTheme.shapes.medium,
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
                    viewOptions.value = !viewOptions.value
                }) {
                    val icon =
                        if (viewOptions.value) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
                    Icon(
                        imageVector = icon,
                        contentDescription = "Show More"
                    )
                }
            }
            AnimatedVisibility(visible = viewOptions.value) {
                Column {
                    TodoTasksListOptions(
                        onDeleteClick = {
                            onEvent(
                                TodoTaskEvent.DeleteTodoTaskGroupClicked(todoTaskGroup)
                            )
                        },
                        onEditClick = {
                            onEvent(
                                TodoTaskEvent.EditTodoTaskGroupClicked(todoTaskGroup)
                            )
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
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Transparent)
            ) {

            }
        }
    }
}