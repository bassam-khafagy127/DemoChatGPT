package com.megatrust.demochatgpt.data.remote


import com.google.gson.annotations.SerializedName

data class CompletionRequest(
    @SerializedName("max_tokens")
    val maxTokens: Int,
    @SerializedName("model")
    val model: String,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("temperature")
    val temperature: Int
)