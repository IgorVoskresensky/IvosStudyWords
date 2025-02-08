package com.ivos.ivos_study_words.di

import de.charlex.settings.datastore.SettingsDataStore
import de.charlex.settings.datastore.create
import org.koin.core.context.startKoin

/**
 *
 * import SwiftUI
 * import shared
 *
 * @main
 * struct iOSApp: App {
 *     var body: some Scene {
 *         WindowGroup {
 *             ContentView()
 *                 .onAppear {
 *                     startKoin()
 *                 }
 *         }
 *     }
 * }
 *
 * func startKoin() {
 *     _ = KoinInitializer().doInitKoin()
 * }
 *
 **/

class KoinInitializer {
    fun doInitKoin() {
        startKoin {
            modules(commonModule + useCasesModule  + iosModule)
        }
    }
}

fun initDatastore() = SettingsDataStore.create(
    name = "ivos_words_datastore.preferences_pb"
)
