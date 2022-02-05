package com.gaproductivity.components.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
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