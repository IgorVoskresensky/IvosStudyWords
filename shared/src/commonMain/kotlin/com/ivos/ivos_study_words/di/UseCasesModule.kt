package com.ivos.ivos_study_words.di

import com.ivos.ivos_study_words.domain.repositories.UserProgressRepository
import com.ivos.ivos_study_words.domain.repositories.WordsRepository
import com.ivos.ivos_study_words.domain.useCases.DeleteProgressByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.DeleteWordByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.GetAllProgressUseCase
import com.ivos.ivos_study_words.domain.useCases.GetAllWordsUseCase
import com.ivos.ivos_study_words.domain.useCases.GetProgressByWordId
import com.ivos.ivos_study_words.domain.useCases.GetWordByIdUseCase
import com.ivos.ivos_study_words.domain.useCases.InsertProgressUseCase
import com.ivos.ivos_study_words.domain.useCases.InsertWordUseCase
import com.ivos.ivos_study_words.domain.useCases.UpdateProgressUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory <GetAllWordsUseCase> {
        GetAllWordsUseCase(get<WordsRepository>())
    }

    factory <GetWordByIdUseCase> {
        GetWordByIdUseCase(get<WordsRepository>())
    }

    factory <InsertWordUseCase> {
        InsertWordUseCase(get<WordsRepository>())
    }

    factory <DeleteWordByIdUseCase> {
        DeleteWordByIdUseCase(get<WordsRepository>())
    }

    factory <GetAllProgressUseCase> {
        GetAllProgressUseCase(get<UserProgressRepository>())
    }

    factory <GetProgressByWordId> {
        GetProgressByWordId(get<UserProgressRepository>())
    }

    factory <InsertProgressUseCase> {
        InsertProgressUseCase(get<UserProgressRepository>())
    }

    factory <UpdateProgressUseCase> {
        UpdateProgressUseCase(get<UserProgressRepository>())
    }

    factory <DeleteProgressByIdUseCase> {
        DeleteProgressByIdUseCase(get<UserProgressRepository>())
    }
}
