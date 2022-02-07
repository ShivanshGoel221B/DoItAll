package com.gaproductivity.doitall.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.doitall.presentation.components.SettingsTopBar
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.surface
    )
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SettingsTopBar(navigator = navigator)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dark Mode",
                    color = MaterialTheme.colors.textColor,
                    fontSize = 18.sp
                )
                Switch(
                    checked = mainViewModel.darkMode.value,
                    onCheckedChange = {
                        mainViewModel.switchMode()
                    }
                )
            }
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Auto Backup Data",
                    color = MaterialTheme.colors.textColor,
                    fontSize = 18.sp
                )
                Switch(
                    checked = mainViewModel.autoBackup.value,
                    onCheckedChange = {
                        mainViewModel.switchAutoBackup()
                    }
                )
            }
        }
    }
}