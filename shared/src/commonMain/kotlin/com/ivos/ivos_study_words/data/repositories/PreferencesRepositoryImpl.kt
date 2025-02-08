package com.ivos.ivos_study_words.data.repositories

import com.ivos.ivos_study_words.data.local.DatastorePreferences
import com.ivos.ivos_study_words.domain.repositories.PreferencesRepository
import de.charlex.settings.datastore.SettingsDataStore
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(
    private val settings: SettingsDataStore,
): PreferencesRepository {

    override suspend fun putString(key: String, value: String) {
        settings.put(DatastorePreferences.preferenceString, value)
    }

    override suspend fun getString(key: String, defaultValue: String): Flow<String> {
        return settings.get(DatastorePreferences.preferenceString)
    }
}
