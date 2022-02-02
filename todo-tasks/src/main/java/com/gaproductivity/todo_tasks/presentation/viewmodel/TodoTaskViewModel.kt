package com.gaproductivity.todo_tasks.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaproductivity.todo_tasks.domain.use_cases.CreateTodoTaskUseCase
import com.gaproductivity.todo_tasks.domain.use_cases.GetAllTodoTasksUseCase
import com.gaproductivity.todo_tasks.domain.use_cases.GetTodoTaskUseCase
import com.gaproductivity.core.domain.UiEvents
import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.todo_tasks.domain.use_cases.GetPendingTasksUseCase
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
    private val getPendingTasksUseCase: GetPendingTasksUseCase,
    private val getTodoTaskUseCase: GetTodoTaskUseCase
): ViewModel() {

    private val _allTodoTasks: MutableState<List<TodoTask>> = mutableStateOf(emptyList())
    val allTodoTasks: State<List<TodoTask>> = _allTodoTasks

    init {
        viewModelScope.launch {
            getAllTodoTasksUseCase.invoke().collect {
                _allTodoTasks.value = it
            }
        }
    }

    private val _uiEvents: Channel<UiEvents> = Channel()
    val uiEvents = _uiEvents.receiveAsFlow()


    fun getPendingTodoTasks(allTodoTasks: List<TodoTask>): List<TodoTask> {
        return getPendingTasksUseCase(allTodoTasks)
    }

}