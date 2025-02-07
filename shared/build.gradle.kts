import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight.driver)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            freeCompilerArgs += "-Xbinary=bundleId=com.ivos.shared"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.sqldelight.async)
            implementation(libs.datetime)
            implementation(libs.koin.core)
            implementation(libs.koin.viewModel)
            implementation(libs.stately.common)
            implementation(libs.datastore)
            implementation(libs.kotlin.serialization)
        }

        androidMain.dependencies {
            implementation(libs.sqldelight.driver.android)
            implementation(libs.koin.android)
            implementation(libs.datastore)
        }

        iosMain.dependencies {
            implementation(libs.sqldelight.driver.native)
            implementation(libs.koin.core)
            implementation(libs.datastore)
        }
    }
}

sqldelight {
    databases {
        create("IvosWordsDatabase") {
            packageName.set("com.ivos.ivos_study_words.database")
        }
    }
}

android {
    namespace = "com.ivos.ivos_study_words"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
