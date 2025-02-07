package com.ivos.ivos_study_words.di

import com.ivos.ivos_study_words.data.DatabaseDriverFactory
import com.ivos.ivos_study_words.database.IvosWordsDatabase
import de.charlex.settings.datastore.SettingsDataStore
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    single<IvosWordsDatabase> {
        val driver = get<DatabaseDriverFactory>().createDriver()
        IvosWordsDatabase(driver)
    }
}
