package com.megatrust.demochatgpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}