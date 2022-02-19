package com.gaproductivity.simple_note.util

import com.gaproductivity.database.entity.NoteBook

sealed class NotesNavigation {
    object ToAddNewNotebook: NotesNavigation()
    data class ToEditNoteBook(val noteBook: NoteBook): NotesNavigation()
}
