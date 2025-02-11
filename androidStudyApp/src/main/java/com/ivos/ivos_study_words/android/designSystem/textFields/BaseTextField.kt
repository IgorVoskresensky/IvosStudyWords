package com.ivos.ivos_study_words.android.designSystem.textFields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivos.ivos_study_words.android.designSystem.theme.Colors.customTextSelectionColors
import com.ivos.ivos_study_words.presentation.values.leftPrimaryGradientCommon

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String = "",
    unfocusedContainerColor: Color = MaterialTheme.colorScheme.background,
    focusedContainerColor : Color= MaterialTheme.colorScheme.background,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 35.dp),
        value = text,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 20.sp,
        ),
        shape = RoundedCornerShape(10.dp),
        label = {
            Text(text = label)
        },
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedContainerColor = unfocusedContainerColor,
            focusedContainerColor = focusedContainerColor,
            focusedIndicatorColor = Color(leftPrimaryGradientCommon),
            cursorColor = Color(leftPrimaryGradientCommon),
            focusedTrailingIconColor = Color(leftPrimaryGradientCommon),
            focusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            textSelectionColors = customTextSelectionColors
        )
    )
}
