package com.gaproductivity.simple_note.domain.filter

sealed class SimpleNotesFilter {
    data class FilterByNoteBook(val noteBookId: Int): SimpleNotesFilter()
    data class FilterBySearch(val searchQuery: String?): SimpleNotesFilter()
    data class FilterByPinned(val isPinned: Boolean = true): SimpleNotesFilter()
}
