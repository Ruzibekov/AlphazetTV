package com.ruzibekov.alphazettv.di

import com.ruzibekov.alphazettv.data.repository.AppRepositoryImpl
import com.ruzibekov.alphazettv.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAppRepository(repo: AppRepositoryImpl): AppRepository
}