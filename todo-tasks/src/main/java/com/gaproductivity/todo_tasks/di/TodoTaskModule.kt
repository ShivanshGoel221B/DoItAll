package com.gaproductivity.todo_tasks.di

import com.gaproductivity.database.Database
import com.gaproductivity.todo_tasks.domain.repository.TodoTaskRepository
import com.gaproductivity.todo_tasks.repository.TodoTaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoTaskModule {

    @Provides
    @Singleton
    fun provideRepository(database: Database): TodoTaskRepository {
        return TodoTaskRepositoryImpl(database.todoTaskDao)
    }

}