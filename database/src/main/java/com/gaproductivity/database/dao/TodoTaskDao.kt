package com.gaproductivity.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.gaproductivity.database.Constants
import com.gaproductivity.database.entity.TodoTask
import com.gaproductivity.database.entity.TodoTaskGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDao {

    @Query("SELECT * FROM ${Constants.TODO_TASK}")
    fun getAllTodoTasks(): Flow<List<TodoTask>>

    @Query("SELECT * FROM ${Constants.TODO_TASK_GROUP}")
    fun getAllTodoTaskGroups(): Flow<List<TodoTaskGroup>>

    @Query("SELECT * FROM ${Constants.TODO_TASK} WHERE todoTaskId = :todoTaskId")
    suspend fun getTodoTaskById(todoTaskId: Int): TodoTask?

    @Query("SELECT * FROM ${Constants.TODO_TASK_GROUP} WHERE todoTaskGroupId = :todoTaskGroupId")
    suspend fun getTodoTaskGroupById(todoTaskGroupId: Int): TodoTaskGroup?

    @Insert(onConflict = REPLACE, entity = TodoTask::class)
    suspend fun createTodoTask(todoTask: TodoTask)

    @Insert(onConflict = REPLACE, entity = TodoTaskGroup::class)
    suspend fun createTodoTaskGroup(todoTaskGroup: TodoTaskGroup)

    @Update(entity = TodoTask::class)
    suspend fun updateTodoTask(todoTask: TodoTask)

    @Update(entity = TodoTaskGroup::class)
    suspend fun updateTodoTaskGroup(todoTaskGroup: TodoTaskGroup)

    @Delete(entity = TodoTask::class)
    suspend fun deleteTodoTask(todoTask: TodoTask)

    @Delete(entity = TodoTaskGroup::class)
    suspend fun deleteTodoTaskGroup(todoTaskGroup: TodoTaskGroup)
}