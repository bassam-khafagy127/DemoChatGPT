package com.megatrust.demochatgpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}