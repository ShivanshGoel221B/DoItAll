package com.gaproductivity.simple_note.presentation.ui.components.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.RemoveDone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaproductivity.components.presentation.theme.orange

@Composable
fun NotebookCardOptions(
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
                    imageVector = Icons.Rounded.DeleteForever,
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
                    imageVector = Icons.Rounded.Edit,
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
                    Icons.Rounded.RemoveDone
                else
                    Icons.Rounded.DoneAll

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