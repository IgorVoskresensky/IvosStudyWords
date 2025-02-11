package com.ivos.ivos_study_words.data.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ivos.ivos_study_words.database.IvosWordsDatabase
import com.ivos.ivos_study_words.domain.models.UserProgressModel
import com.ivos.ivos_study_words.domain.models.WordModel
import com.ivos.ivos_study_words.domain.repositories.WordsRepository
import database.User_progress
import database.Words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

@OptIn(ExperimentalCoroutinesApi::class)
class WordsRepositoryImpl(val db: IvosWordsDatabase) : WordsRepository {
    private val wordsQueries = db.wordsQueries
    private val translationsQueries = db.translationsQueries
    private val exampleSentencesQueries = db.example_sentencesQueries
    private val userProgressQueries = db.user_progressQueries


    override fun getAllWords(): Flow<List<WordModel>> = wordsQueries.getAllWords()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .mapLatest { words ->
            words.mapNotNull { it.toWord() }.map { word ->
                word.id?.let { id ->
                    word.toWordModel(
                        translations = getTranslationsById(id),
                        examples = getExamplesById(id),
                        progress = getProgressById(id)
                    )
                } ?: word.toWordModel()
            }
        }

    override fun getWordById(id: Long) =
        wordsQueries.getWordById(id).executeAsOneOrNull()?.let {
            it.toWord()?.toWordModel(
                translations = getTranslationsById(id),
                examples = getExamplesById(id),
                progress = getProgressById(id)
            )
        }

    override fun insertWord(model: WordModel) {
        db.transaction {
            model.run {
                wordsQueries.insertWord(word, languageCode)
                val wordId = wordsQueries.getLastId().executeAsOneOrNull()
                wordId?.let {
                    userProgressQueries.insertProgress(wordId)

                    translations.forEach {
                        translationsQueries.insertTranslations(wordId, it)
                    }
                    exampleSentences.forEach {
                        exampleSentencesQueries.insertExamples(wordId, it)
                    }
                }
            }
        }
    }

    override fun updateProgress(progress: UserProgressModel) = userProgressQueries.updateProgress(
        word_id = progress.id,
        success_rate = progress.successRate
    )

    override fun deleteProgressByWordId(id: Long) = userProgressQueries.deleteProgressByWordId(id)

    override fun searchWords(query: String): List<WordModel>? {
        return emptyList() //remove if it won't needed
    }

    override fun getWordsByLanguage(code: String): List<WordModel>? {
        return emptyList() //remove if it won't needed
    }

    override fun deleteWordById(id: Long) = wordsQueries.deleteWordById(id)

    private fun getTranslationsById(id: Long) = translationsQueries.getTranslationsById(id)
        .executeAsList().map { it.translation }

    private fun getExamplesById(id: Long) = exampleSentencesQueries.getExamplesById(id)
        .executeAsList().map { it.example_sentence }.toList()

    private fun getProgressById(id: Long) = userProgressQueries.getProgressByWordId(id)
        .executeAsOneOrNull()?.toUserProgressModel()

    private data class Word(
        val id: Long? = null,
        val word: String = "",
        val languageCode: String = "",
        val createdAt: Long? = null,
    )

    private companion object {
        fun Words?.toWord() = this?.let {
            Word(
                id = id,
                word = word,
                languageCode = language_code,
                createdAt = created_at,
            )
        }

        fun User_progress?.toUserProgressModel() = this?.let {
            UserProgressModel(
                id = id,
                wordId = word_id,
                repetitionCount = (repetition_count ?: 0).toInt(),
                lastReviewed = last_reviewed,
                successRate = success_rate ?: 0.0
            )
        }

        fun Word.toWordModel(
            translations: List<String> = emptyList(),
            examples: List<String> = emptyList(),
            progress: UserProgressModel? = null
        ) = WordModel(
            id = id,
            word = word,
            languageCode = languageCode,
            translations = translations,
            exampleSentences = examples,
            progress = progress,
            createdAt = createdAt,
        )
    }
}
