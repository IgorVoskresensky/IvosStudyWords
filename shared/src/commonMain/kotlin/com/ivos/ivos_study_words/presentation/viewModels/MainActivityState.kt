package com.ivos.ivos_study_words.presentation.viewModels

data class BottomBarUiData(
    val word: String = "",
    val translations: List<String> = listOf(""),
    val exampleSentences: List<String> = listOf(""),
)

sealed interface MainActivityUserEvent {
    data class OnNewWordTextChange(
        val text: String,
        val mode: AddingNewWordFiled,
        val index: Int,
    ) : MainActivityUserEvent
    data class OnDeleteText(
        val mode: AddingNewWordFiled,
        val index: Int,
    ) : MainActivityUserEvent

    data object OnAddTranslationClick : MainActivityUserEvent
    data object OnAddExampleClick : MainActivityUserEvent
    data object OnSaveWordClick : MainActivityUserEvent
    data object DismissState : MainActivityUserEvent
}

enum class AddingNewWordFiled {
    WORD, TRANSLATION, EXAMPLE
}



