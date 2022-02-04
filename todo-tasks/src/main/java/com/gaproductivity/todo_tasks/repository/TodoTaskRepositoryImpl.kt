package com.gaproductivity.todo_tasks.repository

import com.gaproductivity.database.dao.TodoTaskDao
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import kotlinx.coroutines.flow.Flow

class TodoTaskRepositoryImpl(private val dao: TodoTaskDao) : TodoTaskRepository {

    override fun getAllTodoTasks(): Flow<List<TodoTask>> {
        return dao.getAllTodoTasks()
    }

    override fun getAllTodoTaskGroups(): Flow<List<TodoTaskGroup>> {
        return dao.getAllTodoTaskGroups()
    }

    override suspend fun getTodoTaskById(todoTaskId: Int): TodoTask? {
        return dao.getTodoTaskById(todoTaskId)
    }

    override suspend fun createTodoTask(todoTask: TodoTask) {
        dao.createTodoTask(todoTask)
    }

    override suspend fun createTodoGroup(todoTaskGroup: TodoTaskGroup) {
        dao.createTodoTaskGroup(todoTaskGroup)
    }

    override suspend fun updateTodoTask(todoTask: TodoTask) {
        dao.updateTodo(todoTask)
    }
}