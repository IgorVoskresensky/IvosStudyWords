package com.ivos.ivos_study_words.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivos.ivos_study_words.android.navigation.NavigationRoutes.LEARN_SCREEN
import com.ivos.ivos_study_words.android.navigation.NavigationRoutes.MAIN_SCREEN
import com.ivos.ivos_study_words.android.screens.MainScreen

@Composable
fun WordsNavHost(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .safeContentPadding()
            .padding(paddingValues),
        navController = navController,
        startDestination = MAIN_SCREEN,
    ) {
        composable(MAIN_SCREEN) {
            MainScreen(navController = navController)
        }

        composable(LEARN_SCREEN) {
            //LearnScreen(navController = navController)
        }
    }
}
