package com.gaproductivity.doitall.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.doitall.presentation.ui.theme.textColor
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel

@Preview
@Composable
fun SwitchModeButton(
    viewModel: MainViewModel = hiltViewModel()
) {
    IconButton(onClick = {
        viewModel.switchMode()
    }) {
        val icon = if (viewModel.darkMode.value)
            Icons.Default.LightMode
        else Icons.Default.DarkMode
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colors.textColor,
            contentDescription = "Switch Mode"
        )
    }
}