package com.ivos.ivos_study_words.data.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ivos.ivos_study_words.database.IvosWordsDatabase
import com.ivos.ivos_study_words.domain.models.UserProgressModel
import com.ivos.ivos_study_words.domain.repositories.UserProgressRepository
import database.User_progress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.map

class UserProgressRepositoryImpl(db: IvosWordsDatabase) : UserProgressRepository {
    private val queries = db.user_progressQueries

    override fun getAllProgress() = queries.getAllProgress()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { list -> list.mapNotNull { it.toUserProgressModel() } }

    override fun getProgressByWordId(id: Long) = queries.getProgressByWordId(id).executeAsOneOrNull()?.toUserProgressModel()

    override fun insertProgress(progress: UserProgressModel) = progress.run {
        queries.insertProgress(
            word_id = wordId,
        )
    }

    override fun updateProgress(progress: UserProgressModel) = queries.updateProgress(
        word_id = progress.id,
        success_rate = progress.successRate
    )

    override fun deleteProgressByWordId(id: Long) = queries.deleteProgressByWordId(id)

    private companion object {
        fun User_progress?.toUserProgressModel() = this?.let {
            UserProgressModel(
                id = id,
                wordId = word_id,
                repetitionCount = (repetition_count ?: 0).toInt(),
                lastReviewed = last_reviewed,
                successRate = success_rate ?: 0.0
            )
        }
    }
}
