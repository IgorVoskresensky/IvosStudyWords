package com.ivos.ivos_study_words.di

import android.content.Context
import de.charlex.settings.datastore.SettingsDataStore
import de.charlex.settings.datastore.create
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(commonModule + useCasesModule + viewModelsModule + androidModule)
    }
}

fun initDatastore(context: Context) = SettingsDataStore.create(
    context = context,
    name = "ivos_words_datastore.preferences_pb"
)
