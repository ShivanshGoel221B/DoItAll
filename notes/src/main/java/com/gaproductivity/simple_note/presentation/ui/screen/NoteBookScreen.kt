package com.gaproductivity.simple_note.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.editor.SimpleTextEditor
import com.gaproductivity.simple_note.presentation.viewmodel.NotesViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun NoteBookScreen(
    navigator: DestinationsNavigator,
    notesViewModel: NotesViewModel = hiltViewModel(),
    topBar: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Text(
                    text = "Create New",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            val textColor = MaterialTheme.colors.textColor

        }
    }
}