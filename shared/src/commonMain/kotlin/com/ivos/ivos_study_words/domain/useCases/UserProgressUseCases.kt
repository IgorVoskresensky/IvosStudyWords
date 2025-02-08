package com.ivos.ivos_study_words.domain.useCases

import com.ivos.ivos_study_words.domain.models.UserProgressModel
import com.ivos.ivos_study_words.domain.repositories.UserProgressRepository

class GetAllProgressUseCase(
    private val userProgressRepository: UserProgressRepository
) {
    operator fun invoke() = userProgressRepository.getAllProgress()
}

class GetProgressByWordId(
    private val userProgressRepository: UserProgressRepository
) {
    operator fun invoke(id: Long) = userProgressRepository.getProgressByWordId(id)
}

class InsertProgressUseCase(
    private val userProgressRepository: UserProgressRepository
) {
    operator fun invoke(progress: UserProgressModel) = userProgressRepository.insertProgress(progress)
}

class UpdateProgressUseCase(
    private val userProgressRepository: UserProgressRepository
) {
    operator fun invoke(progress: UserProgressModel) = userProgressRepository.updateProgress(progress)
}

class DeleteProgressByIdUseCase(
    private val userProgressRepository: UserProgressRepository
) {
    operator fun invoke(id: Long) = userProgressRepository.deleteProgressByWordId(id)
}
