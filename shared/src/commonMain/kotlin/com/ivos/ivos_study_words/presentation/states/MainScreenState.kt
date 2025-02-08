package com.ivos.ivos_study_words.presentation.states

import com.ivos.ivos_study_words.domain.models.WordModel

class MainScreenUiData(
    val words: List<WordModel>,
    val searchText: String = "",
)

sealed interface MainScreenState {
    data object Loading : MainScreenState
    data class Success(val data: MainScreenUiData) : MainScreenState
    data class Error(val message: String) : MainScreenState
}

sealed interface MainScreenStateUserEvent {
    data class AddNewWord(val word: WordModel) : MainScreenStateUserEvent
    data class DoSomethingAndNavigate(
        val id: Long,
        val navigate: () -> Unit,
    ) : MainScreenStateUserEvent
    data class SearchTextChanged(val text: String) : MainScreenStateUserEvent
    data class DeleteWord(val id: Long) : MainScreenStateUserEvent
}
