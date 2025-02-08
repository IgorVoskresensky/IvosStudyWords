package com.ivos.ivos_study_words.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    //for example functions
    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String, defaultValue: String): Flow<String>
}
