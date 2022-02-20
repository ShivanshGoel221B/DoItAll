package com.gaproductivity.simple_note.domain.filter

sealed class NotebookFilter {
    data class SearchByNotebookName(val notebookName: String?): NotebookFilter()
}
