package com.gaproductivity.simple_note.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.database.entity.NoteBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel
@Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel() {

    private val _allNotebooks: MutableState<List<NoteBook>> = mutableStateOf(emptyList())
    val allNotebooks: State<List<NoteBook>>  = _allNotebooks

    init {
        getAllNoteBooks()
    }

    private fun getAllNoteBooks() {

    }
}