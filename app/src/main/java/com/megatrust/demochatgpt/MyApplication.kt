package com.megatrust.demochatgpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){
    companion object {
        const val CHAT_GPT_API_KEY = "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"
    }
}