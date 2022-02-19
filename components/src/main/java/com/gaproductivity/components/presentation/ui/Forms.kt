package com.gaproductivity.components.presentation.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormInputError(
    errorMessage: String?
) {
    errorMessage?.let { message ->
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = message,
            color = MaterialTheme.colors.error,
            fontSize = 11.sp
        )
    }
}