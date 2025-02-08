package com.ivos.ivos_study_words.domain.models

data class UserProgressModel(
    val id: Long,
    val wordId: Long,
    val repetitionCount: Int,
    val lastReviewed: Long?,
    val successRate: Double
)
