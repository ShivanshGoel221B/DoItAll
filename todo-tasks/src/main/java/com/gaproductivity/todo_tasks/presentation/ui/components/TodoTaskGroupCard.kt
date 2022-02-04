package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.components.presentation.theme.translucentGrayColor
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent


@Composable
fun TodoTaskGroupCard(
    modifier: Modifier = Modifier,
    todoTaskGroup: TodoTaskGroup,
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
                    TodoTasksListOptions(model = todoTaskGroup, onEvent = onEvent)
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colors.cardColor))
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