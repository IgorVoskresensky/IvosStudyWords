package com.ivos.ivos_study_words.domain.models

data class WordModel(
    val id: Long? = null,
    val word: String,
    val translation: String,
    val languageCode: String,
    val exampleSentence: String = "",
    val createdAt: Long? = null,
)
