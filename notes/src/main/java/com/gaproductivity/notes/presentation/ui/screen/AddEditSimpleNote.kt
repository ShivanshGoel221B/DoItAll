package com.gaproductivity.notes.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.components.presentation.theme.primaryTranslucent
import com.gaproductivity.components.presentation.theme.textColor
import com.gaproductivity.components.presentation.ui.FormInputError
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.core.domain.Validators
import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.notes.presentation.viewmodel.SimpleNotesViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditSimpleNote(
    navigator: DestinationsNavigator,
    initialSimpleNote: SimpleNote? = null,
    topBar: @Composable () -> Unit,
    simpleNotesViewModel: SimpleNotesViewModel = hiltViewModel()
) {

    initialSimpleNote?.let {
        simpleNotesViewModel.initSimpleNoteForm(it)
    }

    val addEditSimpleNote = simpleNotesViewModel.addEditSimpleNote.value

    LaunchedEffect(key1 = true) {
        simpleNotesViewModel.uiEvents.collect {
            if (it is UiEvents.PopBackStack)
                navigator.popBackStack()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = topBar,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                simpleNotesViewModel.submitSimpleNoteForm()
            }) {
                Text(
                    text = "Save Note",
                    modifier = Modifier.padding(horizontal = 14.dp),
                    color = Color.White
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            contentPadding = PaddingValues(bottom = 110.dp),
        ) {

            // Simple Note Title
            item {
                Text(text = "Enter the Note Title")
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = primaryTranslucent
                    ),
                    shape = MaterialTheme.shapes.medium,
                    value = addEditSimpleNote.noteTitle,
                    onValueChange = {
                        simpleNotesViewModel.updateSimpleNoteForm(
                            addEditSimpleNote.copy(noteTitle = it)
                        )
                    },
                    isError = simpleNotesViewModel.formTitleError.value != null,
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.textColor,
                    ),
                    placeholder = {
                        Text("Enter Note Title")
                    }
                )
                FormInputError(errorMessage = simpleNotesViewModel.formTitleError.value)
            }

            //Simple Note Description
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = "Enter the Note Content")
                    Text(
                        text = "${addEditSimpleNote.noteContent.length}/${Validators.MAX_SIMPLE_NOTE_LENGTH}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = primaryTranslucent
                    ),
                    shape = MaterialTheme.shapes.medium,
                    value = addEditSimpleNote.noteContent,
                    onValueChange = {
                        if (it.length <= Validators.MAX_SIMPLE_NOTE_LENGTH) {
                            simpleNotesViewModel.updateSimpleNoteForm(
                                addEditSimpleNote.copy(noteContent = it)
                            )
                        }
                    },
                    isError = simpleNotesViewModel.formContentError.value != null,
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.textColor,
                    ),
                    placeholder = {
                        Text("Enter the Note Content")
                    },
                    maxLines = 5
                )
                FormInputError(simpleNotesViewModel.formContentError.value)
            }
        }
    }
}