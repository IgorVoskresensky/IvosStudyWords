package com.ivos.ivos_study_words.di

import android.content.Context
import com.ivos.ivos_study_words.data.DatabaseDriverFactory
import org.koin.dsl.module

val androidModule = module {
    single {
        val context = get<Context>()
        DatabaseDriverFactory(context)
    }

    single {
        val context = get<Context>()
        initDatastore(context)
    }
}
