package com.ivos.ivos_study_words.android.designSystem

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ivos.ivos_study_words.android.designSystem.buttons.BaseButton
import com.ivos.ivos_study_words.android.designSystem.textFields.BaseTextField
import com.ivos.ivos_study_words.android.designSystem.textFields.DeletableTextFiled
import com.ivos.ivos_study_words.android.designSystem.theme.Brushes
import com.ivos.ivos_study_words.android.navigation.NavigationRoutes
import com.ivos.ivos_study_words.presentation.viewModels.AddingNewWordFiled
import com.ivos.ivos_study_words.presentation.viewModels.BottomBarUiData
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityUserEvent
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityUserEvent.DismissState
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityUserEvent.OnAddExampleClick
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityUserEvent.OnAddTranslationClick
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityUserEvent.OnNewWordTextChange
import com.ivos.ivos_study_words.presentation.viewModels.MainActivityUserEvent.OnSaveWordClick
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    data: BottomBarUiData,
    produceState: (MainActivityUserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val addingWordVisible = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()

    var pressed by remember { mutableStateOf(false) }
    val floatingButtonSize = animateFloatAsState(
        targetValue = if (pressed) 35f else 55f,
        animationSpec = tween(durationMillis = 500),
        label = "box size"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(Brushes.bottomGradient)
            .drawBehind {
                val w = size.width
                val h = size.height
                val size = Size(floatingButtonSize.value * 3, floatingButtonSize.value * 3)
                val offset = Offset(w / 2 - (size.width / 2), -(size.height / 2))
                val rect = Rect(offset, size)

                val path = Path().apply {
                    addArc(rect, 0f, 200f)
                    close()
                }

                drawPath(path, color = backgroundColor)
            }
            //.border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(8.dp))
            .alpha(0.8f)
    ) {
        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate(NavigationRoutes.MAIN_SCREEN) }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Home,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(onClick = { navController.navigate("") }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.width(floatingButtonSize.value.dp))

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate("") }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Build,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(onClick = { navController.navigate("") }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

    Box(
        modifier = modifier
            .size(floatingButtonSize.value.dp)
            .offset {
                IntOffset(
                    x = ((screenWidth / 2) - (floatingButtonSize.value / 2)).dp.roundToPx() + 2,
                    y = (-(floatingButtonSize.value / 2f).dp).roundToPx()
                )
            }
            .clip(CircleShape)
            .background(Brushes.yellowGradient)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                        addingWordVisible.value = true
                    },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = modifier.size(25.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "Add new word",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }

    if (addingWordVisible.value) {
        ModalBottomSheet(
            modifier = Modifier
                .padding(0.dp),
            onDismissRequest = {
                scope.launch {
                    addingWordVisible.value = false
                    produceState(DismissState)
                    sheetState.hide()
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            sheetState = sheetState
        ) {
            AddWordBottomContent(
                data = data,
                produceState = produceState,
                onDismiss = {
                    scope.launch {
                        addingWordVisible.value = false
                        produceState(DismissState)
                        sheetState.hide()
                    }
                }
            )
        }
    }
}

@Composable
fun AddWordBottomContent(
    modifier: Modifier = Modifier,
    data: BottomBarUiData,
    produceState: (MainActivityUserEvent) -> Unit,
    onDismiss: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp),
            text = "Add word",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        BaseTextField(
            modifier = Modifier
                .padding(bottom = 16.dp),
            text = data.word,
            label = "Write the word or phrase",
            onValueChange = { value ->
                produceState(OnNewWordTextChange(value, AddingNewWordFiled.WORD, 0))
            }
        )

        Text(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            text = "Translations:",
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .animateContentSize(),
                contentPadding = PaddingValues(vertical = 2.dp),
            ) {
                itemsIndexed(items = data.translations, key = { index, _ -> index }) { index, item ->
                    DeletableTextFiled(
                        text = item,
                        label = "Write ${index + 1} translation",
                        enableToDelete = index != 0,
                        onDelete = {
                            produceState(MainActivityUserEvent.OnDeleteText(AddingNewWordFiled.TRANSLATION, index))
                        },
                        onValueChange = { value ->
                            produceState(OnNewWordTextChange(value, AddingNewWordFiled.TRANSLATION, index))
                        }
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            BaseButton(
                size = DpSize(width = 56.dp, height = 40.dp),
                iconColor = MaterialTheme.colorScheme.onBackground,
                iconContainerBackground = MaterialTheme.colorScheme.primary,
                iconContainerPadding = PaddingValues(4.dp),
                onClick = { produceState(OnAddTranslationClick) },
            )
        }

        Text(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            text = "Examples:",
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .animateContentSize(),
                contentPadding = PaddingValues(vertical = 2.dp),
            ) {
                itemsIndexed(items = data.exampleSentences, key = { index, _ -> index }) { index, item ->
                    DeletableTextFiled(
                        text = item,
                        label = "Write ${index + 1}  example",
                        enableToDelete = index != 0,
                        onDelete = {
                            produceState(MainActivityUserEvent.OnDeleteText(AddingNewWordFiled.EXAMPLE, index))
                        },
                        onValueChange = { value ->
                            produceState(OnNewWordTextChange(value, AddingNewWordFiled.EXAMPLE, index))
                        }
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            BaseButton(
                size = DpSize(width = 56.dp, height = 40.dp),
                iconColor = MaterialTheme.colorScheme.onBackground,
                iconContainerBackground = MaterialTheme.colorScheme.primary,
                iconContainerPadding = PaddingValues(4.dp),
                onClick = { produceState(OnAddExampleClick) },
            )
        }

        BaseButton(
            label = "Save",
            icon = Icons.Default.Check,
            background = MaterialTheme.colorScheme.primary,
            enabled = data.word.isNotBlank() && data.translations.any { it.isNotBlank() },
            onClick = {
                produceState(OnSaveWordClick)
                onDismiss()
            }
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}
