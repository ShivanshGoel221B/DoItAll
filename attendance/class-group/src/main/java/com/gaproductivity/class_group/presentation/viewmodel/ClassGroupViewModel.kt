package com.gaproductivity.class_group.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.class_group.domain.use_cases.CreateClassGroupUseCase
import com.gaproductivity.class_group.domain.use_cases.GetAllGroupsUseCase
import com.gaproductivity.class_group.domain.use_cases.GetGroupUseCase
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.ClassGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassGroupViewModel @Inject constructor(
    private val createClassGroupUseCase: CreateClassGroupUseCase,
    private val getAllGroupsUseCase: GetAllGroupsUseCase,
    private val getGroupUseCase: GetGroupUseCase
): ViewModel() {

    private val _uiEvents: Channel<UiEvents> = Channel()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun testDB() {
        viewModelScope.launch {
            createClassGroupUseCase.invoke(
                ClassGroup(
                classGroupName = "Testing"
            )
            )
        }
    }

}