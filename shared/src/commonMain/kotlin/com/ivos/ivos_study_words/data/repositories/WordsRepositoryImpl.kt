package com.ivos.ivos_study_words.data.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ivos.ivos_study_words.database.IvosWordsDatabase
import com.ivos.ivos_study_words.domain.models.WordModel
import com.ivos.ivos_study_words.domain.repositories.WordsRepository
import database.Words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map

class WordsRepositoryImpl(db: IvosWordsDatabase) : WordsRepository {
    private val queries = db.wordsQueries

    override fun getAllWords() = queries.getAllWords()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { list -> list.mapNotNull { it.toWordModel() } }

    override fun getWordById(id: Long) = queries.getWordById(id).executeAsOneOrNull()?.toWordModel()

    override fun insertWord(model: WordModel) = model.run {
        queries.insertWord(
            word = word,
            translation = translation,
            language_code = languageCode,
            example_sentence = exampleSentence,
        )
    }

    override fun searchWords(query: String): List<WordModel>? {
        return emptyList() //remove if it won't needed
    }

    override fun getWordsByLanguage(code: String): List<WordModel>? {
        return emptyList() //remove if it won't needed
    }

    override fun deleteWordById(id: Long) = queries.deleteWordById(id)

    private companion object {
        fun Words?.toWordModel() = this?.let {
            WordModel(
                id = id,
                word = word,
                translation = translation,
                languageCode = language_code,
                exampleSentence = example_sentence,
                createdAt = created_at,
            )
        }
    }
}
