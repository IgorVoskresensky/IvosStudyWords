package com.ivos.ivos_study_words.di

import com.ivos.ivos_study_words.data.DatabaseDriverFactory
import org.koin.dsl.module

val iosModule = module {
    single {
        DatabaseDriverFactory()
    }

    single {
        initDatastore()
    }
}
