package com.gaproductivity.doitall.presentation.components

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.doitall.domain.event.MenuEvent
import com.gaproductivity.doitall.presentation.components.destinations.SettingsNavDestination
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    screenTitle: String = "Do It All",
    viewModel: MainViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    onHome: Boolean = false
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
        var showMenu by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = true) {
            viewModel.menuEvent.collect { menuEvent ->
                when (menuEvent) {
                    is MenuEvent.ExitApp -> {
                        (context as Activity).finishAffinity()
                    }
                    is MenuEvent.ToggleMenu -> {
                        showMenu = !showMenu
                    }
                }
            }
        }

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
                        imageVector = Icons.Rounded.ExitToApp,
                        tint = MaterialTheme.colors.textColor,
                        contentDescription = "Exit"
                    )
                }
            else
                IconButton(onClick = {
                    viewModel.toggleMenu()
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
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
                    imageVector = Icons.Rounded.Settings,
                    tint = MaterialTheme.colors.textColor,
                    contentDescription = "Setting Icon"
                )
            }
        }

        AnimatedVisibility(visible = showMenu) {
            Divider()
            MainMenu(modifier = Modifier.fillMaxWidth(), navigator = navigator)
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
                imageVector = Icons.Rounded.ArrowBack,
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