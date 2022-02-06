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
    private val getAllTodoTaskGroupsUseCase: GetAllTodoTaskGroupsUseCase,
    private val getTodoTaskUseCase: GetTodoTaskUseCase,
    private val deleteTodoTaskGroupUseCase: DeleteTodoTaskGroupUseCase,
    private val deleteTodoTaskUseCase: DeleteTodoTaskUseCase,
    private val filterTodoTasksUseCase: FilterTodoTasksUseCase
): ViewModel() {

    private val _allTodoTasks: MutableState<List<TodoTask>> = mutableStateOf(emptyList())
    val allTodoTasks: State<List<TodoTask>> = _allTodoTasks

    private val _allTodoTaskGroup: MutableState<List<TodoTaskGroup>> = mutableStateOf(emptyList())
    val allTodoTaskGroups: State<List<TodoTaskGroup>> = _allTodoTaskGroup

    private val _createEditTodoGroup: MutableState<TodoTaskGroup> = mutableStateOf(
        TodoTaskGroup(
            todoTaskGroupName = ""
        )
    )
    val createTodoGroup: State<TodoTaskGroup> = _createEditTodoGroup

    private val _formNameError: MutableState<Boolean> = mutableStateOf(false)

    val formNameError: State<Boolean> = _formNameError
    private val _formDescriptionError: MutableState<Boolean> = mutableStateOf(false)

    val formDescriptionError: State<Boolean> = _formDescriptionError

    val editTodoTaskGroup: MutableState<TodoTaskGroup?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            getAllTodoTasksUseCase().collect {
                _allTodoTasks.value = it
            }
        }
        viewModelScope.launch {
            getAllTodoTaskGroupsUseCase().collect {
                _allTodoTaskGroup.value = it
            }
        }
    }

    private val _uiEvents: Channel<UiEvents> = Channel()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun initUpdateTodoGroup(todoTaskGroup: TodoTaskGroup) {
        _createEditTodoGroup.value = todoTaskGroup
    }

    fun updateTodoGroupFormName(newName: String) {
        val updatedTodoTaskGroup = TodoTaskGroup(todoTaskGroupName = newName)
        try {
            _formNameError.value = false
            _createEditTodoGroup.value = _createEditTodoGroup.value.copy(
                todoTaskGroupName = updatedTodoTaskGroup.todoTaskGroupName
            )
            Validators.validateName(_createEditTodoGroup.value.todoTaskGroupName)
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

    fun getPendingGroupTodoTasks(allTodoTasks: List<TodoTask>, todoTaskGroupId: Int): List<TodoTask> {
        return filterTodoTasksUseCase(
            allTodoTasks,
            TodoTaskFilter.FilterByGroup(todoTaskGroupId),
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
            is TodoTaskEvent.SubmitTodoTaskGroup -> {
                if (!_formNameError.value) {
                    viewModelScope.launch {
                        createTodoTaskGroupUseCase(
                            _createEditTodoGroup.value
                        )
                    }.invokeOnCompletion {
                        sendEvent(
                            UiEvents.PopBackStack
                        )
                    }
                }
            }
            is TodoTaskEvent.DeleteTodoTaskGroupClicked -> {
                toDeleteTodoTaskGroup = todoTaskEvent.todoTaskGroup
                _showDeleteDialog.value = true
            }
            is TodoTaskEvent.ConfirmTodoTaskGroupDelete -> {
                deleteTodoTaskGroup()
            }
            is TodoTaskEvent.ConfirmTodoTaskDelete -> {

            }
            is TodoTaskEvent.DismissTodoTaskDelete -> {

            }
            is TodoTaskEvent.DismissTodoTaskGroupDelete -> {
                toDeleteTodoTaskGroup = null
                _showDeleteDialog.value = false
            }
            is TodoTaskEvent.ItemDeleted -> {
                toDeleteTodoTaskGroup = null
                _showDeleteDialog.value = false
                sendEvent(
                    UiEvents.ShowSnackBar(
                        message = "${todoTaskEvent.itemName} Deleted Successfully"
                    )
                )
            }

            else -> Unit
        }
    }

    private val _showDeleteDialog = mutableStateOf(false)
    val showDeleteDialog: State<Boolean> = _showDeleteDialog

    private fun sendEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }

    //DB Functions

    private var toDeleteTodoTaskGroup: TodoTaskGroup? = null

    private fun deleteTodoTaskGroup() {
        toDeleteTodoTaskGroup?.let { todoTaskGroup ->
            viewModelScope.launch {
                deleteTodoTaskGroupUseCase(todoTaskGroup)
            }.invokeOnCompletion {
                onEvent(
                    TodoTaskEvent.ItemDeleted(todoTaskGroup.todoTaskGroupName)
                )
            }
        }
    }

    fun deleteTodoTask(todoTask: TodoTask) {
        viewModelScope.launch {
            deleteTodoTaskUseCase(todoTask)
        }.invokeOnCompletion {
            onEvent(
                TodoTaskEvent.ItemDeleted(todoTask.todoTaskTitle)
            )
        }
    }



}