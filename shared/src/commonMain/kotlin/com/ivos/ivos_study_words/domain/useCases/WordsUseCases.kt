package com.ivos.ivos_study_words.domain.useCases

import com.ivos.ivos_study_words.domain.models.WordModel
import com.ivos.ivos_study_words.domain.repositories.WordsRepository

class GetAllWordsUseCase(
    private val wordsRepository: WordsRepository
) {
    operator fun invoke() = wordsRepository.getAllWords()
}

class GetWordByIdUseCase(
    private val wordsRepository: WordsRepository
) {
    operator fun invoke(id: Long) = wordsRepository.getWordById(id)
}

class InsertWordUseCase(
    private val wordsRepository: WordsRepository
) {
    operator fun invoke(wordModel: WordModel) = wordsRepository.insertWord(wordModel)
}

class DeleteWordByIdUseCase(
    private val wordsRepository: WordsRepository
) {
    operator fun invoke(id: Long) = wordsRepository.deleteWordById(id)
}
