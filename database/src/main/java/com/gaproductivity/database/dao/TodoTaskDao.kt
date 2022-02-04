package com.gaproductivity.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
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

    @Insert(onConflict = REPLACE, entity = TodoTask::class)
    suspend fun createTodoTask(todoTask: TodoTask)

    @Insert(onConflict = REPLACE, entity = TodoTaskGroup::class)
    suspend fun createTodoTaskGroup(todoTaskGroup: TodoTaskGroup)

    @Update
    suspend fun updateTodo(todoTask: TodoTask)

}