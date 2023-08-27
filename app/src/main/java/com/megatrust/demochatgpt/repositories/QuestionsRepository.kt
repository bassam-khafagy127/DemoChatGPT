package com.megatrust.demochatgpt.repositories

import android.util.Log
import com.megatrust.demochatgpt.data.remote.CompletionRequest
import com.megatrust.demochatgpt.networking.ChatGPTApiService
import com.megatrust.demochatgpt.utills.Constant.LOG_TAG_RETROFIT
import javax.inject.Inject

class QuestionsRepository @Inject constructor(
    private val chatGptApiService: ChatGPTApiService
) {

    suspend fun getApiQuestion(question: String) {
        val completionRequest = CompletionRequest(
            maxTokens = 4000,
            model = "text-davinci-003",
            prompt = "$question",
            temperature = 0
        )
        val response = chatGptApiService.getCompletion(completionRequest)
        if (response.isSuccessful) {
            Log.d(LOG_TAG_RETROFIT,"Success")
        }else Log.d(LOG_TAG_RETROFIT,"Error")
    }


}