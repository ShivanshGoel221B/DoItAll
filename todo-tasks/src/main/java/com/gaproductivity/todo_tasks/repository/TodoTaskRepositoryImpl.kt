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

    override suspend fun getTodoTaskGroupById(todoTaskGroupId: Int): TodoTaskGroup? {
        return dao.getTodoTaskGroupById(todoTaskGroupId)
    }

    override suspend fun createTodoTask(todoTask: TodoTask) {
        dao.createTodoTask(todoTask)
    }

    override suspend fun createTodoTaskGroup(todoTaskGroup: TodoTaskGroup) {
        dao.createTodoTaskGroup(todoTaskGroup)
    }

    override suspend fun updateTodoTask(todoTask: TodoTask) {
        dao.updateTodoTask(todoTask)
    }

    override suspend fun updateTodoTaskGroup(todoTaskGroup: TodoTaskGroup) {
        dao.updateTodoTaskGroup(todoTaskGroup)
    }

    override suspend fun deleteTodoTaskGroup(todoTaskGroup: TodoTaskGroup) {
        dao.deleteTodoTaskGroup(todoTaskGroup)
    }

    override suspend fun deleteTodoTask(todoTask: TodoTask) {
        dao.deleteTodoTask(todoTask)
    }
}