package com.gaproductivity.doitall.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.doitall.presentation.screens.HomeScreen
import com.gaproductivity.doitall.presentation.ui.theme.DoItAllTheme
import com.gaproductivity.doitall.presentation.viewmodel.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

val Context.preferenceStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}


@Composable
@Destination(start = true)
fun BaseContent(navigator: DestinationsNavigator) {
    val viewModel: MainViewModel = hiltViewModel()
    DoItAllTheme(darkTheme = viewModel.darkMode.value) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.surface
        )
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreen(navigator)
        }
    }
}