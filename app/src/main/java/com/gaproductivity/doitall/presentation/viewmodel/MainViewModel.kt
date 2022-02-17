package com.gaproductivity.doitall.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.doitall.domain.event.MenuEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel() {

    private val darkModeKey = booleanPreferencesKey("darkMode")
    private val autoBackupKey = booleanPreferencesKey("autoBackup")

    private val _darkMode: MutableState<Boolean> = mutableStateOf(true)
    val darkMode: State<Boolean> = _darkMode

    private val _autoBackup: MutableState<Boolean> = mutableStateOf(true)
    val autoBackup: State<Boolean> = _autoBackup

    private val _menuEvent: Channel<MenuEvent> = Channel()
    val menuEvent = _menuEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            dataStore.data.collect {
                _darkMode.value = it[darkModeKey] ?: true
                _autoBackup.value = it[autoBackupKey] ?: true
            }
        }
    }

    fun switchMode() {
        val currentMode = _darkMode.value
        viewModelScope.launch {
            dataStore.edit {
                it[darkModeKey] = !currentMode
                _darkMode.value = !currentMode
            }
        }
    }

    fun switchAutoBackup() {
        val currentPref = _autoBackup.value
        viewModelScope.launch {
            dataStore.edit {
                it[autoBackupKey] = !currentPref
                _autoBackup.value = !currentPref
            }
        }
    }

    fun toggleMenu() {
        sendEvent(MenuEvent.ToggleMenu)
    }

    fun exitApp() {
        sendEvent(MenuEvent.ExitApp)
    }

    private fun sendEvent(event: MenuEvent) {
        viewModelScope.launch {
            _menuEvent.send(event)
        }
    }
}