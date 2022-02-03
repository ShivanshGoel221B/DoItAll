package com.gaproductivity.doitall.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.doitall.presentation.ui.theme.textColor
import com.gaproductivity.doitall.presentation.ui.theme.translucentGray
import com.gaproductivity.todo_tasks.presentation.viewmodel.TodoTaskViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun PendingTasks(
    modifier: Modifier = Modifier,
    viewModel: TodoTaskViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        Row {
            Text(
                text = "Pending Tasks",
                color = MaterialTheme.colors.textColor
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Row {
            Card(
                border = BorderStroke(0.5.dp, MaterialTheme.colors.translucentGray),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 0.dp
            ) {
                LazyRow(
                    modifier = Modifier
                        .padding(6.dp)
                        .height(110.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    viewModel.getPendingTodoTasks(
                        viewModel.allTodoTasks.value
                    ).forEach { todoTask ->
                        item(key = todoTask.todoTaskId) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Card(
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier.clickable {
//                                    onNavigate(UiEvents.Navigate(""))
                                    // TODO: Add Route
                                },
                                backgroundColor = Color.White
                            ) {
                                Box(
                                    modifier = Modifier.size(100.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(6.dp),
                                        text = todoTask.todoTaskTitle,
                                        color = MaterialTheme.colors.textColor,
                                        fontSize = 22.sp,
                                        textAlign = TextAlign.Center,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                }
            }
        }
    }
}