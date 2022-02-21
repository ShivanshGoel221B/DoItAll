package com.gaproductivity.notes.presentation.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaproductivity.notes.presentation.ui.components.card.NotebookCard
import com.gaproductivity.notes.presentation.viewmodel.NotesViewModel
import com.gaproductivity.notes.util.NotesNavigation


@Composable
fun NotebookList(
    modifier: Modifier = Modifier,
    notesViewModel: NotesViewModel = hiltViewModel(),
    isRichNotes: Boolean = false,
    notesNavigation: (NotesNavigation) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 110.dp)
    ) {
        notesViewModel.allNotebooks.value.forEach { noteBook ->
            item {
                Spacer(modifier = Modifier.height(4.dp))

                NotebookCard(noteBook = noteBook, notesNavigation = notesNavigation)

                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}