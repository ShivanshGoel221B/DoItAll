package com.gaproductivity.notes.domain.filter

sealed class NotebookFilter {
    data class SearchByNotebookName(val notebookName: String?): NotebookFilter()
}
