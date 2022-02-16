package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveDone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.orange

@Composable
fun TodoTasksListOptions(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onMarkClick: () -> Unit,
    isComplete: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteForever,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Delete",
                    fontSize = 10.sp,
                    color = Color.Red
                )
            }
        }

        IconButton(
            onClick = onEditClick,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Edit",
                    fontSize = 10.sp,
                    color = MaterialTheme.colors.primary
                )
            }
        }

        IconButton(
            onClick = onMarkClick,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val icon = if (isComplete)
                    Icons.Default.RemoveDone
                else
                    Icons.Default.DoneAll

                val iconTint = if (isComplete)
                    orange
                else
                    Color.Green

                Icon(
                    imageVector = icon,
                    contentDescription = "Mark All as Done",
                    tint = iconTint
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (isComplete) "Mark Undone" else "Mark Done",
                    fontSize = 10.sp,
                    color = if (isComplete) orange else Color.Green
                )
            }
        }

    }
}