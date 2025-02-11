package com.ivos.ivos_study_words.android.designSystem.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.ivos.ivos_study_words.android.designSystem.theme.Colors.leftPrimaryGradient
import com.ivos.ivos_study_words.android.designSystem.theme.Colors.rightPrimaryGradient
import com.ivos.ivos_study_words.android.designSystem.theme.Colors.whiteSurface
import com.ivos.ivos_study_words.presentation.values.leftPrimaryGradientCommon
import com.ivos.ivos_study_words.presentation.values.rightPrimaryGradientCommon
import com.ivos.ivos_study_words.presentation.values.whiteSurfaceCommon

object Brushes {
    val yellowGradient = Brush.linearGradient(
        listOf(leftPrimaryGradient, rightPrimaryGradient)
    )

    val bottomGradient = Brush.linearGradient(
        listOf(whiteSurface, rightPrimaryGradient, whiteSurface)
    )
}

object Colors {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color(rightPrimaryGradientCommon),
        backgroundColor = Color(rightPrimaryGradientCommon),
    )

    val whiteSurface = Color(whiteSurfaceCommon)
    val leftPrimaryGradient = Color(leftPrimaryGradientCommon)
    val rightPrimaryGradient = Color(rightPrimaryGradientCommon)
}
