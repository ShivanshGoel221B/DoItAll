package com.gaproductivity.doitall.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gaproductivity.doitall.presentation.components.UpcomingTasks
import com.gaproductivity.doitall.presentation.components.TopBar
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
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState(
                    consumeScrollDelta = { it }
                )
            )
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
        UpcomingTasks(todoNavigation = todoNavigation)
        Spacer(modifier = Modifier.size(2.dp))
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
}