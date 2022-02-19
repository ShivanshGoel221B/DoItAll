package com.gaproductivity.simple_note.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.theme.translucentGrayColor
import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.simple_note.presentation.viewmodel.NotesViewModel
import com.gaproductivity.simple_note.util.NotesNavigation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun AddEditNotebook(
    navigator: DestinationsNavigator,
    initialNotebook: NoteBook? = null,
    notesViewModel: NotesViewModel = hiltViewModel(),
    notesNavigation: (NotesNavigation) -> Unit,
    topBar: @Composable () -> Unit
) {
    initialNotebook?.let {
        notesViewModel.initializeEditNotebook(it)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Text(
                    text = "Create Notebook",
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
            Text(text = "Enter the name of the notebook")
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                value = notesViewModel.addEditNotebook.value.noteBookName,
                onValueChange = notesViewModel::updateNotebookForm,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = primaryTranslucent
                ),
                placeholder = {
                    Text(text = "Enter the name of notebook", color = translucentGrayColor)
                }
            )
        }
    }
}