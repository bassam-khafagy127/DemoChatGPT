package com.megatrust.demochatgpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}