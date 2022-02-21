package com.gaproductivity.notes.domain.use_case.notebook

import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.notes.domain.filter.NotebookFilter
import javax.inject.Inject

class FilterNotebooksUseCase
@Inject constructor() {

    operator fun invoke(
        allNotebooks: List<NoteBook>,
        vararg notebooksFilters: NotebookFilter
    ): List<NoteBook> {
        var filteredList: List<NoteBook> = allNotebooks
        notebooksFilters.forEach { filter ->
            when(filter) {
                is NotebookFilter.SearchByNotebookName -> {
                    filteredList = filteredList.filter { noteBook ->
                        nameMatch(noteBook, filter.notebookName)
                    }
                }
            }
        }
        return filteredList
    }

    private fun nameMatch(noteBook: NoteBook, searchName: String?): Boolean {
        if (searchName == null)
            return true
        if (noteBook.noteBookName.lowercase() == searchName.lowercase())
            return true
        if (noteBook.noteBookName.lowercase().contains(searchName.lowercase()))
            return true
        if (searchName.lowercase().contains(noteBook.noteBookName.lowercase()))
            return true
        val delimiters = listOf(" ", ".", ",", "-").toTypedArray()

        for (nameFrag in noteBook.noteBookName.split(*delimiters)) {
            for (searchFrag in searchName.split(*delimiters)) {

                if (searchFrag.lowercase() == nameFrag.lowercase())
                    return true
                if (nameFrag.lowercase().contains(searchFrag.lowercase()))
                    return true
                if (searchFrag.lowercase().contains(nameFrag.lowercase()))
                    return true
            }
        }
        return false
    }

}