package com.ivos.ivos_study_words.android.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ivos.ivos_study_words.android.designSystem.BottomNavigationBar
import com.ivos.ivos_study_words.android.designSystem.theme.MyApplicationTheme
import com.ivos.ivos_study_words.android.navigation.WordsNavHost
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val currentBackStackEntry by navController.currentBackStackEntryAsState()
                    val uiDate by viewModel.uiData.collectAsStateWithLifecycle()

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                data = uiDate,
                                produceState = viewModel::produceState,
                            )
                        }
                    ) {
                        WordsNavHost(it, navController)
                    }
                }
            }
        }
    }
}
