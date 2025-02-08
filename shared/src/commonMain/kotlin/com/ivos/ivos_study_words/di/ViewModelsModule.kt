package com.ivos.ivos_study_words.di

import com.ivos.ivos_study_words.domain.useCases.DeleteWordByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.GetAllWordsUseCase
import com.ivos.ivos_study_words.domain.useCases.GetWordByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.InsertWordUseCase
import com.ivos.ivos_study_words.presentation.viewModels.MainScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainScreenViewModel(
            getAllWordsUseCase = get<GetAllWordsUseCase>(),
            getWordByIdUseCase = get<GetWordByIdUseCase>(),
            insertWordUseCase = get<InsertWordUseCase>(),
            deleteWordByIdUseCase = get<DeleteWordByIdUseCase>()
        )
    }
}
