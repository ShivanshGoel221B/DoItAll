package com.gaproductivity.notes.util

import com.gaproductivity.database.entity.NoteBook

sealed class NotesNavigation {
    object ToAddNewNotebook: NotesNavigation()
    object ToAddNewSimpleNote: NotesNavigation()
    data class ToEditNoteBook(val noteBook: NoteBook): NotesNavigation()
    data class ToSimpleNotesList(val notebookId: Int): NotesNavigation()
}
