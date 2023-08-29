package com.megatrust.demochatgpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}