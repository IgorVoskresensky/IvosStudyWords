package com.ivos.ivos_study_words.domain.repositories

import com.ivos.ivos_study_words.domain.models.UserProgressModel
import com.ivos.ivos_study_words.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getAllWords(): Flow<List<WordModel>>
    fun getWordById(id: Long): WordModel?
    fun insertWord(model: WordModel)
    fun searchWords(query: String): List<WordModel>?  //probably it isn't needed
    fun getWordsByLanguage(code: String): List<WordModel>?  //probably it isn't needed
    fun updateProgress(progress: UserProgressModel)
    fun deleteProgressByWordId(id: Long)
    fun deleteWordById(id: Long)
}
