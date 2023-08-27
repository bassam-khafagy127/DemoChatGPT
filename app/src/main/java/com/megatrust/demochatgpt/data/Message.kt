package com.megatrust.demochatgpt.data

data class Message(val message: String, val sender: String) {
    companion object {
        val SENT_BY_ME = "user"
        val SENT_BY_BOT = "bot"
    }
}
