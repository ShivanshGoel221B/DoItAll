package com.gaproductivity.todo_tasks.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.filter.TodoTaskFilter
import com.gaproductivity.todo_tasks.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoTaskViewModel @Inject constructor(
    private val createTodoTaskUseCase: CreateTodoTaskUseCase,
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
        _createTodoGroup.value = updatedTodoTaskGroup
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

}