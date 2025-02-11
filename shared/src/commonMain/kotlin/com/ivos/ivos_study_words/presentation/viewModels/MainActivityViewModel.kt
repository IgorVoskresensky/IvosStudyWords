package com.ivos.ivos_study_words.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ivos.ivos_study_words.domain.models.WordModel
import com.ivos.ivos_study_words.domain.useCases.InsertWordUseCase
import com.ivos.ivos_study_words.presentation.viewModels.AddingNewWordFiled.EXAMPLE
import com.ivos.ivos_study_words.presentation.viewModels.AddingNewWordFiled.TRANSLATION
import com.ivos.ivos_study_words.presentation.viewModels.AddingNewWordFiled.WORD
import com.ivos.ivos_study_words.utils.extentions.executeSuspend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update

class MainActivityViewModel(
    private val insertWordUseCase: InsertWordUseCase,
) : ViewModel() {

    private val _bottomUiData: MutableStateFlow<BottomBarUiData> =
        MutableStateFlow(BottomBarUiData())
    val uiData
        get() = _bottomUiData.asStateFlow()

    private val word = MutableStateFlow("")
    private val wordTranslation = MutableStateFlow(listOf(""))
    private val wordExamples = MutableStateFlow(listOf(""))

    init {
        combineFlows()
    }

    fun produceState(event: MainActivityUserEvent) {
        when (event) {
            is MainActivityUserEvent.OnNewWordTextChange -> changeNewWord(event.text, event.mode, event.index)
            is MainActivityUserEvent.OnDeleteText -> deleteText(event.mode, event.index)
            is MainActivityUserEvent.OnAddTranslationClick -> wordTranslation.update { it + "" }
            is MainActivityUserEvent.OnAddExampleClick -> wordExamples.update { it + "" }
            is MainActivityUserEvent.OnSaveWordClick -> insertWord()
            is MainActivityUserEvent.DismissState -> dropState()
        }
    }

    private fun insertWord() {
        with(_bottomUiData.value) {
            executeSuspend {
                insertWordUseCase(
                    WordModel(
                        word = word,
                        translations = translations,
                        exampleSentences = exampleSentences
                    )
                )
            }
        }
    }

    private fun changeNewWord(text: String, mode: AddingNewWordFiled, index: Int) {
        when (mode) {
            WORD -> word.update { text }
            TRANSLATION -> wordTranslation.update {
                it.mapIndexed { i, s -> if (i == index) text else s }
            }
            EXAMPLE -> wordExamples.update {
                it.mapIndexed { i, s -> if (i == index) text else s }
            }
        }
    }

    private fun deleteText(mode: AddingNewWordFiled, index: Int) {
        when (mode) {
            WORD -> word
            TRANSLATION -> wordTranslation.update {
                val list = it.toMutableList()
                list.removeAt(index)
                list
            }
            EXAMPLE -> wordExamples.update {
                val list = it.toMutableList()
                list.removeAt(index)
                list
            }
        }
    }

    private fun dropState() {
        word.update { "" }
        wordTranslation.update { listOf("") }
        wordExamples.update { listOf("") }
        _bottomUiData.update { BottomBarUiData() }
    }

    private fun combineFlows() {
        executeSuspend {
            combine(
                word, wordTranslation, wordExamples
            ) { newWord, newWordTranslation, newWordExamples ->
                BottomBarUiData(
                    word = newWord,
                    translations = newWordTranslation,
                    exampleSentences = newWordExamples
                )
            }.collect {
                _bottomUiData.value = it
            }
        }
    }
}
