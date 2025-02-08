package com.ivos.ivos_study_words.android.designSystem

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ivos.ivos_study_words.presentation.values.leftPrimaryGradient
import com.ivos.ivos_study_words.presentation.values.rightPrimaryGradient

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val color = MaterialTheme.colorScheme.background
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    var pressed by remember { mutableStateOf(false) }
    val floatingButtonSize = animateFloatAsState(
        targetValue = if (pressed) 65f else 75f,
        animationSpec = tween(durationMillis = 500),
        label = "box size"
    )

    val brush = remember {
        Brush.linearGradient(
            listOf(Color(leftPrimaryGradient), Color(rightPrimaryGradient))
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .offset {
                IntOffset(
                    x = 0,
                    y = (floatingButtonSize.value / 3.8).dp.roundToPx()
                )
            }
            .background(brush)
            .drawBehind {
                val w = size.width
                val h = size.height
                val size = Size(230f, 230f)
                val offset = Offset(w / 2 - (size.width / 2), -(size.height / 2))
                val rect = Rect(offset, size)

                val path = Path().apply {
                    addArc(rect, 0f, 200f)
                    close()
                }

                drawPath(path, color = color)
            }
    ) {
        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate("") }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Home,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            IconButton(onClick = { navController.navigate("") }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onPrimary
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
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            IconButton(onClick = { navController.navigate("") }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = "Add new word",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }

    Box(
        modifier = modifier
            .size(floatingButtonSize.value.dp)
            .offset {
                IntOffset(
                    x = ((screenWidth / 2) - (floatingButtonSize.value / 2)).dp.roundToPx(),
                    y = (-(floatingButtonSize.value / 4).dp).roundToPx()
                )
            }
            .clip(CircleShape)
            .background(brush)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                    }
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = modifier.size(50.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "Add new word",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    val color = MaterialTheme.colorScheme.background
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(
                brush = Brush.linearGradient(
                    listOf(Color(leftPrimaryGradient), Color(rightPrimaryGradient))
                )
            )
            .drawBehind {
                val w = size.width
                val h = size.height

                val size = Size(300f, 300f)
                val offset = Offset(w / 2 - 150f, -(size.height / 2))
                val rect = Rect(offset, size)

                val path = Path().apply {
                    addArc(rect, 0f, 360f)
                    close()
                }
                drawPath(path, color = color)
            }

    ) {

    }
}
