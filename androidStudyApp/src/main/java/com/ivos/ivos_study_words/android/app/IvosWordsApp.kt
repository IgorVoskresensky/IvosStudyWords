package com.ivos.ivos_study_words.android.app

import android.app.Application
import com.ivos.ivos_study_words.di.initKoin

class IvosWordsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(context = this)
    }
}
