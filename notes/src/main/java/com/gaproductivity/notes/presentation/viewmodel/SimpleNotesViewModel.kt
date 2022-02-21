package com.gaproductivity.notes.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.core.domain.Validators
import com.gaproductivity.database.entity.SimpleNote
import com.gaproductivity.notes.domain.filter.SimpleNotesFilter
import com.gaproductivity.notes.domain.use_case.simple.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
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

    val maxContentLength = 2000

    private val _allSimpleNotes = mutableStateOf(emptyList<SimpleNote>())
    val allSimpleNote: State<List<SimpleNote>> = _allSimpleNotes

    private val _addEditSimpleNote = mutableStateOf(SimpleNote(
        noteBookId = 0,
        noteTitle = "",
        noteContent = "",
        createdAt = 0
    ))
    val addEditSimpleNote: State<SimpleNote> = _addEditSimpleNote

    private val _formTitleError: MutableState<String?> = mutableStateOf(null)
    val formTitleError: State<String?> = _formTitleError

    private val _formContentError: MutableState<String?> = mutableStateOf(null)
    val formContentError: State<String?> = _formContentError

    init {
        viewModelScope.launch {
            getAllSimpleNotesUseCase().collect {
                _allSimpleNotes.value = it
            }
        }
    }

    fun initSimpleNoteForm(simpleNote: SimpleNote) {
        _addEditSimpleNote.value = simpleNote
    }

    fun updateSimpleNoteForm(simpleNote: SimpleNote) {
        _formTitleError.value = null
        _formContentError.value = null

        _addEditSimpleNote.value = simpleNote
        try {
            Validators.validateName(simpleNote.noteTitle)
        } catch (e: InputMismatchException) {
            _formTitleError.value = e.message
        }
        try {
            Validators.validateSimpleNoteContent(simpleNote.noteContent)
        } catch (e: InputMismatchException) {
            _formContentError.value = e.message
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