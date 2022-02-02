package com.gaproductivity.doitall.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.doitall.presentation.ui.theme.cardColor
import com.gaproductivity.doitall.presentation.ui.theme.textColor
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    screenTitle: String = "Do It All",
    viewModel: MainViewModel = hiltViewModel()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            viewModel.switchMode()
        }) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint = MaterialTheme.colors.textColor,
                contentDescription = "Switch Mode"
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = screenTitle,
            color = MaterialTheme.colors.textColor,
            fontSize = 18.sp,
            modifier = Modifier.width(0.dp).weight(1f, true),
        )

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

}