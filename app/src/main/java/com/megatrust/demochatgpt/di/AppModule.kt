package com.megatrust.demochatgpt.di

import android.content.Context
import com.megatrust.demochatgpt.networking.ChatGPTApiService
import com.megatrust.demochatgpt.repositories.QuestionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuestionsRepository(
        @ApplicationContext appContext: Context,
        chatGptApiService: ChatGPTApiService
    ) = QuestionsRepository(chatGptApiService)


}
