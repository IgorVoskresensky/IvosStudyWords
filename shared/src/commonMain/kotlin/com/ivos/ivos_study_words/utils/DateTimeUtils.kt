package com.ivos.ivos_study_words.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateTimeUtils {
    fun now() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    fun toEpochMillis(dateTime: LocalDateTime) = dateTime
        .toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}
