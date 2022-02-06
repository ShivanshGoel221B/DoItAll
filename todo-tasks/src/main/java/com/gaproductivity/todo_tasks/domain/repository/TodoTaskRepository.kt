package com.gaproductivity.todo_tasks.domain.repository

import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import kotlinx.coroutines.flow.Flow

interface TodoTaskRepository {
    fun getAllTodoTasks(): Flow<List<TodoTask>>
    fun getAllTodoTaskGroups(): Flow<List<TodoTaskGroup>>
    suspend fun getTodoTaskById(todoTaskId: Int): TodoTask?
    suspend fun createTodoTask(todoTask: TodoTask)
    suspend fun createTodoTaskGroup(todoTaskGroup: TodoTaskGroup)
    suspend fun updateTodoTask(todoTask: TodoTask)
    suspend fun updateTodoTaskGroup(todoTaskGroup: TodoTaskGroup)
    suspend fun deleteTodoTaskGroup(todoTaskGroup: TodoTaskGroup)
    suspend fun deleteTodoTask(todoTask: TodoTask)
}