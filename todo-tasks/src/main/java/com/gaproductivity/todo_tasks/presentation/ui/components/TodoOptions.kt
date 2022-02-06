package com.gaproductivity.todo_tasks.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TodoTasksListOptions(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
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
            Icon(
                imageVector = Icons.Default.DeleteForever,
                contentDescription = "Delete",
                tint = Color.Red
            )
        }

        IconButton(
            onClick = onEditClick,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = MaterialTheme.colors.primary
            )
        }

    }
}