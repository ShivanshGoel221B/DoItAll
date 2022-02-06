package com.gaproductivity.components.presentation.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.gaproductivity.components.presentation.theme.cardColor
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.core.domain.DialogModel

@Composable
fun DialogBox(
    dialogModel: DialogModel
) {
    AlertDialog(
        onDismissRequest = dialogModel.onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dialogModel.autoDismissible,
            dismissOnClickOutside = dialogModel.autoDismissible
        ),
        backgroundColor = MaterialTheme.colors.cardColor,
        shape = RoundedCornerShape(14.dp),
        title = {
            Text(
                text = dialogModel.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp
            )
        },
        text = {
            Text(
                text = dialogModel.message,
                color = MaterialTheme.colors.textColor,
            )
        },
        confirmButton = {
            TextButton(onClick = dialogModel.onPositive) {
                Text(
                    text = dialogModel.positiveText,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center
                )
            }
        },
        dismissButton = {
            TextButton(onClick = dialogModel.onNegative) {
                Text(
                    text = dialogModel.negativeText,
                    color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center
                )
            }
        }
    )

}