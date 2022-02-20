package com.gaproductivity.simple_note.domain.use_case.simple

import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.simple_note.domain.filter.SimpleNotesFilter
import javax.inject.Inject

class FilterSimpleNoteUseCase
@Inject constructor() {

    operator fun invoke(
        allSimpleNotes: List<SimpleNote>,
        vararg simpleNoteFilters: SimpleNotesFilter
    ): List<SimpleNote> {
        var filteredList: List<SimpleNote> = allSimpleNotes
        simpleNoteFilters.forEach { filter ->
            when(filter) {
                is SimpleNotesFilter.FilterByNoteBook -> {
                    filteredList = filteredList.filter { simpleNote ->
                        simpleNote.simpleNoteId == filter.noteBookId
                    }
                }
                is SimpleNotesFilter.FilterByPinned -> {
                    filteredList = filteredList.filter { simpleNote ->
                        simpleNote.isPinned == filter.isPinned
                    }
                }
                is SimpleNotesFilter.FilterBySearch -> {
                    filteredList = filteredList.filter { simpleNote ->
                        nameMatch(simpleNote, filter.searchQuery)
                    }
                }
            }
        }
        return filteredList
    }

    private fun nameMatch(simpleNote: SimpleNote, searchName: String?): Boolean {
        if (searchName == null)
            return true
        if (simpleNote.noteTitle.lowercase() == searchName.lowercase())
            return true
        if (simpleNote.noteTitle.lowercase().contains(searchName.lowercase()))
            return true
        if (searchName.lowercase().contains(simpleNote.noteTitle.lowercase()))
            return true
        val delimiters = listOf(" ", ".", ",", "-").toTypedArray()

        for (nameFrag in simpleNote.noteTitle.split(*delimiters)) {
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