package com.gaproductivity.todo_tasks.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.core.domain.Validators
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.filter.TodoTaskFilter
import com.gaproductivity.todo_tasks.domain.use_cases.*
import com.gaproductivity.todo_tasks.presentation.event.TodoTaskEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TodoTaskViewModel @Inject constructor(
    private val createTodoTaskUseCase: CreateTodoTaskUseCase,
    private val createTodoTaskGroupUseCase: CreateTodoTaskGroupUseCase,
    private val getAllTodoTasksUseCase: GetAllTodoTasksUseCase,
    private val getTodoTaskUseCase: GetTodoTaskUseCase,
    private val filterTodoTasksUseCase: FilterTodoTasksUseCase
): ViewModel() {

    private val _allTodoTasks: MutableState<List<TodoTask>> = mutableStateOf(emptyList())
    val allTodoTasks: State<List<TodoTask>> = _allTodoTasks

    private val _createTodoGroup: MutableState<TodoTaskGroup> = mutableStateOf(
        TodoTaskGroup(
            todoTaskGroupName = ""
        )
    )

    private val _formNameError: MutableState<Boolean> = mutableStateOf(false)
    val formNameError: State<Boolean> = _formNameError

    private val _formDescriptionError: MutableState<Boolean> = mutableStateOf(false)
    val formDescriptionError: State<Boolean> = _formDescriptionError

    val createTodoGroup: State<TodoTaskGroup> = _createTodoGroup

    init {
        viewModelScope.launch {
            getAllTodoTasksUseCase.invoke().collect {
                _allTodoTasks.value = it
            }
        }
    }

    private val _uiEvents: Channel<UiEvents> = Channel()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun updateCreateGroup(newName: String) {
        val updatedTodoTaskGroup = TodoTaskGroup(todoTaskGroupName = newName)
        try {
            _formNameError.value = false
            _createTodoGroup.value = updatedTodoTaskGroup
            Validators.validateName(_createTodoGroup.value.todoTaskGroupName)
        } catch (e: InputMismatchException) {
            _formNameError.value = true
        }
    }

    fun getPendingTodoTasks(allTodoTasks: List<TodoTask>): List<TodoTask> {
        return filterTodoTasksUseCase(
            allTodoTasks,
            TodoTaskFilter.FilterByPending(true)
        )
    }

    fun getTodoTasksByGroupId(groupId: Int): List<TodoTask> {
        return filterTodoTasksUseCase(
            allTodoTasks.value,
            TodoTaskFilter.FilterByGroup(groupId)
        )
    }

    fun onEvent(todoTaskEvent: TodoTaskEvent) {
        when(todoTaskEvent) {
            is TodoTaskEvent.SubmitNewTodoTaskGroup -> {
                if (!_formNameError.value) {
                    viewModelScope.launch {
                        createTodoTaskGroupUseCase(
                            _createTodoGroup.value
                        )
                    }.invokeOnCompletion {
                        sendEvent(
                            UiEvents.PopBackStack
                        )
                    }
                }
            }

            else -> Unit
        }
    }

    private fun sendEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }

}