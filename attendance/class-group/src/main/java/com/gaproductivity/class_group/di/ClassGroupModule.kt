package com.gaproductivity.class_group.di

import com.gaproductivity.class_group.data.repository.ClassGroupRepositoryImpl
import com.gaproductivity.class_group.domain.repository.ClassGroupRepository
import com.gaproductivity.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassGroupModule {

    @Provides
    @Singleton
    fun provideRepository(database: Database): ClassGroupRepository {
        return ClassGroupRepositoryImpl(database.classGroupDao)
    }

}