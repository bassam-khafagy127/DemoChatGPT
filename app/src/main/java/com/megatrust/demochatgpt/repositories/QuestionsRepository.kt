package com.megatrust.demochatgpt.repositories

import com.megatrust.demochatgpt.data.remote.CompletionRequest
import com.megatrust.demochatgpt.data.remote.CompletionResponse
import com.megatrust.demochatgpt.networking.ChatGPTApiService
import retrofit2.Response
import javax.inject.Inject

class QuestionsRepository @Inject constructor(
    private val chatGptApiService: ChatGPTApiService
) {

    suspend fun getApiQuestion(question: String): Response<CompletionResponse> {
        val completionRequest = CompletionRequest(
            maxTokens = 2000,
            model = "text-davinci-003",
            prompt = "$question",
            temperature = 0
        )
        return chatGptApiService.getCompletion(completionRequest)
    }




}