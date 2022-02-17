package com.gaproductivity.doitall.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Task
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.components.presentation.theme.translucentGrayColor
import com.gaproductivity.doitall.presentation.components.destinations.TodoTasksGroupsScreenNavDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuButton(icon = Icons.Default.Task, text = "Tasks") {
                navigator.navigate(
                    TodoTasksGroupsScreenNavDestination
                )
            }
            MenuButton(icon = Icons.Default.Event, text = "Attendance") {
                navigator.navigate(
                    TodoTasksGroupsScreenNavDestination
                )
            }
            MenuButton(icon = Icons.Default.QuestionAnswer, text = "Flash Cards") {
                navigator.navigate(
                    TodoTasksGroupsScreenNavDestination
                )
            }
        }
    }
}

@Composable
fun MenuButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(width = 0.5.dp, color = translucentGrayColor),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(3.dp).size(72.dp)
    ) {
        IconButton(onClick = onClick, modifier = Modifier.fillMaxSize().padding(4.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Menu Button Icon",
                    modifier = Modifier.size(36.dp),
                    tint = primaryTranslucent
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = text, fontSize = 10.sp, color = primaryTranslucent)
            }
        }
    }
}