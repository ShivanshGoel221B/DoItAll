package com.gaproductivity.doitall.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.doitall.presentation.components.destinations.SettingsNavDestination
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    screenTitle: String = "Do It All",
    viewModel: MainViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    onHome: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onHome)
            IconButton(onClick = {
                viewModel.exitApp()
            }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    tint = MaterialTheme.colors.textColor,
                    contentDescription = "Exit"
                )
            }
        else
            IconButton(onClick = {
                viewModel.toggleMenu()
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = MaterialTheme.colors.textColor,
                    contentDescription = "Menu Icon"
                )
            }

        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = screenTitle,
            color = MaterialTheme.colors.textColor,
            fontSize = 18.sp,
            modifier = Modifier
                .width(0.dp)
                .weight(1f, true),
        )

        IconButton(onClick = {
            navigator.navigate(
                SettingsNavDestination
            )
        }) {
            Icon(
                imageVector = Icons.Default.Settings,
                tint = MaterialTheme.colors.textColor,
                contentDescription = "Setting Icon"
            )
        }
    }

}


@Composable
fun SettingsTopBar(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navigator.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = MaterialTheme.colors.textColor,
                contentDescription = "Go Back"
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = "Settings",
            color = MaterialTheme.colors.textColor,
            fontSize = 18.sp,
            modifier = Modifier
                .width(0.dp)
                .weight(1f, true),
        )
    }

}