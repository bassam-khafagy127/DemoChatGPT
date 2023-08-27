package com.megatrust.demochatgpt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megatrust.demochatgpt.repositories.QuestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionsRepository) :
    ViewModel() {

    fun getApiAnswer(question: String) {
        viewModelScope.launch {
            repository.getApiQuestion(question)
        }
    }
}