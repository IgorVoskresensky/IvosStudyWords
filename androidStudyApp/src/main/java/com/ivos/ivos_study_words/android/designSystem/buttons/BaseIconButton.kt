package com.ivos.ivos_study_words.android.designSystem.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ivos.ivos_study_words.presentation.values.leftPrimaryGradientCommon

@Composable
fun BaseIconButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Cyan.copy(alpha = 0.5f),
    enabled: Boolean = true,
    onClickSave: () -> Unit,
) {
    Button(
        modifier = Modifier
            .height(45.dp),
        colors = ButtonDefaults.buttonColors().copy(
            disabledContentColor = Color.Gray.copy(alpha = 0.5f),
            containerColor = Color(leftPrimaryGradientCommon)
        ),
        shape = RoundedCornerShape(16.dp),
        enabled = enabled,
        onClick = onClickSave
    ) {
        Text(text = "Save")

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Default.Done,
            contentDescription = "Add note"
        )
    }
}
