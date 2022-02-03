package com.gaproductivity.class_group.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.class_group.presentation.viewmodel.ClassGroupViewModel
import com.gaproductivity.core.domain.UiEvents
import kotlinx.coroutines.flow.collect


@Composable
fun ClassGroupsListScreen(
    modifier: Modifier = Modifier,
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: ClassGroupViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvents.collect { event: UiEvents ->
            when(event) {
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                is UiEvents.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        modifier = modifier.padding(16.dp).fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        FloatingActionButton(
            onClick = {
                viewModel.testDB()
            }
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 14.dp),
                text = "Add New Group",
                color = Color.White
            )
        }

    }
}