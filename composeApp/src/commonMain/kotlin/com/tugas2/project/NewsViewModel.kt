package com.tugas2.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NewsUiState(
    val items: List<String> = emptyList(),
    val totalCount: Int = 0
)

class NewsViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            newsFeedSimulator()
                .map { "Breaking [${it.category}]: ${it.title}" }
                .collect { newNewsItem ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            items = (listOf(newNewsItem) + currentState.items).take(20),
                            totalCount = currentState.totalCount + 1
                        )
                    }
                }
        }
    }
}
