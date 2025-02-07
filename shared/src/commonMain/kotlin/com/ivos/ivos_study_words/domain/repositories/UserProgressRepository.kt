package com.ivos.ivos_study_words.domain.repositories

import com.ivos.ivos_study_words.domain.models.UserProgressModel
import kotlinx.coroutines.flow.Flow

interface UserProgressRepository {
    fun getAllProgress(): Flow<List<UserProgressModel>>
    fun getProgressByWordId(id: Long): UserProgressModel?
    fun insertProgress(progress: UserProgressModel)
    fun updateProgress(id: Long, progress: UserProgressModel)
    fun deleteProgressByWordId(id: Long)
}
