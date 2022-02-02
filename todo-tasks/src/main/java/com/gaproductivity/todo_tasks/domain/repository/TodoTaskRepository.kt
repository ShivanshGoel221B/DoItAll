package com.gaproductivity.todo_tasks.domain.repository

import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import kotlinx.coroutines.flow.Flow

interface TodoTaskRepository {
    fun getAllTodos(): Flow<List<TodoTask>>
    suspend fun getTodoTaskById(todoTaskId: Int): TodoTask?
    suspend fun createTodoTask(todoTask: TodoTask)
    suspend fun createTodoGroup(todoTaskGroup: TodoTaskGroup)
    suspend fun updateTodoTask(todoTask: TodoTask)
}