package com.gaproductivity.todo_tasks.repository

import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import com.gaproductivity.database.dao.ClassGroupDao
import com.gaproductivity.database.dao.TodoTaskDao
import com.gaproductivity.database.entity.ClassGroup
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import kotlinx.coroutines.flow.Flow

class TodoTaskRepositoryImpl(private val dao: TodoTaskDao) : TodoTaskRepository {

    override fun getAllTodos(): Flow<List<TodoTask>> {
        return dao.getAllTodoTasks()
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