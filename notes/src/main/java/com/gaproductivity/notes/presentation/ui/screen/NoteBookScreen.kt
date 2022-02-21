package com.gaproductivity.notes.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.notes.presentation.ui.components.NotebookList
import com.gaproductivity.notes.presentation.viewmodel.NotesViewModel
import com.gaproductivity.notes.util.NotesNavigation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun NoteBookScreen(
    navigator: DestinationsNavigator,
    isRichNotes: Boolean = false,
    notesViewModel: NotesViewModel = hiltViewModel(),
    notesNavigation: (NotesNavigation) -> Unit,
    topBar: @Composable () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                notesNavigation(NotesNavigation.ToAddNewNotebook)
            }) {
                Text(
                    text = "Create New",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {
            NotebookList(notesNavigation = notesNavigation, isRichNotes = isRichNotes)
        }
    }
}