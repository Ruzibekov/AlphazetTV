package com.ruzibekov.alphazettv.di

import android.content.Context
import com.ruzibekov.alphazettv.data.repository.SettingsRepositoryImpl
import com.ruzibekov.alphazettv.data.store.SettingsDataStore
import com.ruzibekov.alphazettv.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): SettingsDataStore = SettingsDataStore(context)

    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataStore: SettingsDataStore
    ): SettingsRepository = SettingsRepositoryImpl(settingsDataStore)
}