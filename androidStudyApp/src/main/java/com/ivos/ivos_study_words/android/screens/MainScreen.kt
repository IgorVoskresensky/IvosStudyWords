package com.ivos.ivos_study_words.android.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ivos.ivos_study_words.domain.models.WordModel
import com.ivos.ivos_study_words.presentation.states.MainScreenState
import com.ivos.ivos_study_words.presentation.states.MainScreenUiData
import com.ivos.ivos_study_words.presentation.viewModels.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("NewApi")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val screenState = viewModel.screenState.collectAsStateWithLifecycle()

    when (val state = screenState.value) {
        is MainScreenState.Loading -> CircularProgressIndicator()
        is MainScreenState.Success -> MainScreenContent(
            uiData = state.data,
            onAddWordClick = {}
        )
        is MainScreenState.Error -> {
            //todo error screen
        }
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    uiData: MainScreenUiData,
    onAddWordClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        MainScreenEmptyContent(visible = uiData.words.isEmpty())

        MainScreenFullyContent(
            visible = uiData.words.isNotEmpty(),
            uiData = uiData,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenFullyContent(
    modifier: Modifier = Modifier,
    visible: Boolean,
    uiData: MainScreenUiData,
) {
    AnimatedVisibility(visible) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            if (uiData.words.isNotEmpty()) {
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        text = "My words",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(uiData.words, key = { it.id ?: 0 }) { word ->
                HorizontalDivider()

                WordsListItem(word = word)

                if (word == uiData.words.last()) {
                    HorizontalDivider()
                }
            }

            if (uiData.words.isNotEmpty()) {
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        text = "Saved words",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(uiData.words, key = { "${it.id ?: 0} + ${it.word}" }) { word ->
                HorizontalDivider()

                WordsListItem(word = word)

                if (word == uiData.words.last()) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordsListItem(
    modifier: Modifier = Modifier,
    word: WordModel,
) {
    Card(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp,
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                modifier = modifier
                    .padding(bottom = 4.dp),
                text = word.word,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            FlowRow {
                Text(
                    text = "Translations:",
                    fontSize = 12.sp,
                )

                word.translations.forEachIndexed { index, item ->
                    Text(
                        text = " $item ${ if (index != word.translations.lastIndex) "|" else ""}",
                        fontSize = 12.sp,
                    )
                }
            }

            if (word.exampleSentences.first().isNotBlank()) {
                Text(
                    text = "Examples:",
                    fontSize = 12.sp,
                )
            }

            FlowRow {
                word.exampleSentences.forEachIndexed { index, item ->
                    if (item.isNotBlank()) {
                        Text(
                            text = "${index + 1}) $item ${if (index != word.translations.lastIndex) "|" else ""}",
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreenEmptyContent(
    modifier: Modifier = Modifier,
    visible: Boolean,
) {

    AnimatedVisibility(visible = visible) {
        Box(
            modifier = modifier
                .fillMaxWidth(fraction = 0.8f)
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No data yet\n Please add some words to\n start studying",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        }
    }
}
