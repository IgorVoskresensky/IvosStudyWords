package com.ivos.ivos_study_words

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform