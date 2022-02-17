package com.gaproductivity.doitall.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gaproductivity.doitall.presentation.components.MainMenu
import com.gaproductivity.doitall.presentation.components.TopBar
import com.gaproductivity.doitall.presentation.components.UpcomingTasks
import com.gaproductivity.doitall.presentation.components.destinations.TodoTasksGroupsScreenNavDestination
import com.gaproductivity.todo_tasks.presentation.ui.components.TodoNavigation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    todoNavigation: (TodoNavigation) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            TopBar(navigator = navigator, onHome = true)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                UpcomingTasks(todoNavigation = todoNavigation)
                Spacer(modifier = Modifier.size(2.dp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "View All",
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 4.dp)
                            .clickable {
                                navigator.navigate(direction = TodoTasksGroupsScreenNavDestination)
                            }
                    )
                }
            }
            item {
                MainMenu(
                    navigator = navigator,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        }
    }

}