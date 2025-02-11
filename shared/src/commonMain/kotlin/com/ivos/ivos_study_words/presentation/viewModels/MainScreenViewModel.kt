package com.ivos.ivos_study_words.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.ivos.ivos_study_words.domain.models.WordModel
import com.ivos.ivos_study_words.domain.useCases.DeleteWordByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.GetAllWordsUseCase
import com.ivos.ivos_study_words.domain.useCases.GetWordByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.InsertWordUseCase
import com.ivos.ivos_study_words.presentation.states.MainScreenState
import com.ivos.ivos_study_words.presentation.states.MainScreenStateUserEvent
import com.ivos.ivos_study_words.presentation.states.MainScreenUiData
import com.ivos.ivos_study_words.utils.extentions.executeSuspend
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update

class MainScreenViewModel(
    private val getAllWordsUseCase: GetAllWordsUseCase,
    private val getWordByIdUseCase: GetWordByIdUseCase,
    private val insertWordUseCase: InsertWordUseCase,
    private val deleteWordByIdUseCase: DeleteWordByIdUseCase,
) : ViewModel() {

    private val _screenState: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Loading)
    val screenState
        get() = _screenState.asStateFlow()

    private val allWords = MutableStateFlow<List<WordModel>>(emptyList())
    private val searchText = MutableStateFlow("")

    init {
        getAllWords()
        combineFlows()
    }

    fun produceState(event: MainScreenStateUserEvent) {
        when (event) {
            is MainScreenStateUserEvent.AddNewWord -> insertWordUseCase(event.word)
            is MainScreenStateUserEvent.SearchTextChanged -> searchText.update { event.text }
            is MainScreenStateUserEvent.DeleteWord -> deleteWordByIdUseCase(event.id)
            is MainScreenStateUserEvent.DoSomethingAndNavigate -> { event.navigate() }

        }
    }

    private fun getAllWords() {
        executeSuspend {
            getAllWordsUseCase.invoke().collect { list -> allWords.update { list } }
        }
    }

    private fun combineFlows() {
        executeSuspend {
            combine(
                allWords, searchText
            ) { allWords, searchText ->
                println("allWords, $allWords")
                MainScreenState.Success(
                    MainScreenUiData(
                        words = allWords,
                        searchText = searchText,
                    )
                )
            }.collect {
                _screenState.value = it
            }
        }
    }
}
