package com.ivos.ivos_study_words.domain.models

data class WordModel(
    val id: Long? = null,
    val word: String = "",
    val languageCode: String = "EN",
    val translations: List<String> = emptyList(),
    val exampleSentences:List<String> = emptyList(),
    val progress: UserProgressModel? = null,
    val createdAt: Long? = null,
)
