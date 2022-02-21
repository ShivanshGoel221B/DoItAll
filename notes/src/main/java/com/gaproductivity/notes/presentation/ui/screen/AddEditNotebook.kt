package com.gaproductivity.notes.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.ui.FormInputError
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.notes.presentation.viewmodel.NotesViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect


@Composable
fun AddEditNotebook(
    navigator: DestinationsNavigator,
    initialNotebook: NoteBook? = null,
    notesViewModel: NotesViewModel = hiltViewModel(),
    topBar: @Composable () -> Unit
) {
    initialNotebook?.let {
        notesViewModel.initializeEditNotebook(it)
    }

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        notesViewModel.uiEvents.collect {
            when(it) {
                is UiEvents.PopBackStack -> {
                    navigator.popBackStack()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = notesViewModel::submitNoteForm) {
                Text(
                    text = "Save Notebook",
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
                    Text(text = "Enter the name of notebook")
                }
            )
            FormInputError(errorMessage = notesViewModel.formInputNameError.value)
        }
    }
}