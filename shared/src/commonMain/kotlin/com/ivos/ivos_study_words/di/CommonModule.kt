package com.ivos.ivos_study_words.di

import com.ivos.ivos_study_words.data.DatabaseDriverFactory
import com.ivos.ivos_study_words.data.repositories.UserProgressRepositoryImpl
import com.ivos.ivos_study_words.data.repositories.WordsRepositoryImpl
import com.ivos.ivos_study_words.database.IvosWordsDatabase
import com.ivos.ivos_study_words.domain.repositories.UserProgressRepository
import com.ivos.ivos_study_words.domain.repositories.WordsRepository
import org.koin.dsl.module

val commonModule = module {
    single<IvosWordsDatabase> {
        val driver = get<DatabaseDriverFactory>().createDriver()
        IvosWordsDatabase(driver)
    }

    single<WordsRepository> {
        WordsRepositoryImpl(get<IvosWordsDatabase>())
    }

    single<UserProgressRepository> {
        UserProgressRepositoryImpl(get<IvosWordsDatabase>())
    }
}
