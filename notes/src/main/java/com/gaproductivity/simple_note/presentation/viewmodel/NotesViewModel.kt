package com.gaproductivity.simple_note.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.core.domain.Validators
import com.gaproductivity.database.entity.NoteBook
import com.gaproductivity.simple_note.domain.use_case.notebook.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NotesViewModel
@Inject constructor(
    private val createNotebookUseCase: CreateNotebookUseCase,
    private val filterNotebooksUseCase: FilterNotebooksUseCase,
    private val getAllNotebooksUseCase: GetAllNotebooksUseCase,
    private val getNotebookByIdUseCase: GetNotebookByIdUseCase,
    private val updateNotebookUseCase: UpdateNotebookUseCase
): ViewModel() {

    private val _allNotebooks: MutableState<List<NoteBook>> = mutableStateOf(emptyList())
    val allNotebooks: State<List<NoteBook>>  = _allNotebooks

    private val _addEditNotebook: MutableState<NoteBook> =
        mutableStateOf(NoteBook(noteBookName = ""))
    val addEditNotebook: State<NoteBook> = _addEditNotebook

    private val _formInputNameError: MutableState<String?> = mutableStateOf(null)
    val formInputNameError: State<String?> = _formInputNameError

    private val _uiEvents: Channel<UiEvents> = Channel()
    val uiEvents = _uiEvents.receiveAsFlow()

    init {
        getAllNoteBooks()
    }

    private fun getAllNoteBooks() {
        viewModelScope.launch {
            getAllNotebooksUseCase().collect {
                _allNotebooks.value = it
            }
        }
    }

    fun updateNotebookForm(newName: String) {
        _formInputNameError.value = null
        _addEditNotebook.value = addEditNotebook.value.copy(noteBookName = newName)
        try {
            Validators.validateName(newName)
        } catch (e: InputMismatchException) {
            _formInputNameError.value = e.message
        }
    }
    fun initializeEditNotebook(noteBook: NoteBook) {
        _addEditNotebook.value = noteBook
    }

    fun submitNoteForm() {
        if(_formInputNameError.value == null) {
            viewModelScope.launch {
                createNotebookUseCase(_addEditNotebook.value)
            }.invokeOnCompletion {
                sendEvent(UiEvents.PopBackStack)
            }
        }
    }

    private fun sendEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }
}