package com.gaproductivity.notes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.notes.domain.filter.SimpleNotesFilter
import com.gaproductivity.notes.domain.use_case.simple.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimpleNotesViewModel
@Inject constructor(
    private val createSimpleNoteCase: CreateSimpleNoteCase,
    private val filterSimpleNoteUseCase: FilterSimpleNoteUseCase,
    private val getAllSimpleNotesUseCase: GetAllSimpleNotesUseCase,
    private val getSimpleNoteByIdUseCase: GetSimpleNoteByIdUseCase,
    private val updateSimpleNoteUseCase: UpdateSimpleNoteUseCase
): ViewModel() {

    private val _allSimpleNotes = mutableStateOf(emptyList<SimpleNote>())
    val allSimpleNote: State<List<SimpleNote>> = _allSimpleNotes

    init {
        viewModelScope.launch {
            getAllSimpleNotesUseCase().collect {
                _allSimpleNotes.value = it
            }
        }
    }

    fun getSimpleNotesByNotebook(
        allNotes: List<SimpleNote> = _allSimpleNotes.value,
        noteBookId: Int
    ): List<SimpleNote> {
        return filterSimpleNoteUseCase(
            allSimpleNotes = allNotes,
            SimpleNotesFilter.FilterByNoteBook(noteBookId)
        )
    }

    fun getPinnedSimpleNotes(
        allNotes: List<SimpleNote> = _allSimpleNotes.value
    ): List<SimpleNote> {
        return filterSimpleNoteUseCase(
            allSimpleNotes = allNotes,
            SimpleNotesFilter.FilterByPinned()
        )
    }

}