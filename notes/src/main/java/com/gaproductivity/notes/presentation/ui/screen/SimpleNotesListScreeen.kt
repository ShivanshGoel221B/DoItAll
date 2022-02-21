package com.gaproductivity.notes.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.notes.presentation.viewmodel.SimpleNotesViewModel
import com.gaproductivity.notes.util.NotesNavigation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun SimpleNotesListScreen(
    notebookId: Int,
    topBar: @Composable () -> Unit,
    notesNavigation: (NotesNavigation) -> Unit,
    simpleNotesViewModel: SimpleNotesViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    var simpleNotes by remember {
        mutableStateOf(
            simpleNotesViewModel.getSimpleNotesByNotebook(
                noteBookId = notebookId
            )
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                notesNavigation(NotesNavigation.ToAddNewSimpleNote)
            }) {
                Text(
                    text = "Create New",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        }
    ) {

    }
}