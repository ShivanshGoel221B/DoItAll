package com.gaproductivity.editor.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimpleViewModel @Inject constructor(): ViewModel() {

    private val _currentString = mutableStateOf("")
    val currentString: State<String> = _currentString

    fun updateCurrentString(updatedString: String) {
        _currentString.value = updatedString
    }

}