package com.ivos.ivos_study_words.android.designSystem.textFields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeletableTextFiled(
    modifier: Modifier = Modifier,
    text: String,
    label: String = "",
    enableToDelete: Boolean = true,
    onDelete: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    SwipeToDismissBox(
        state = rememberSwipeToDismissBoxState(
            confirmValueChange = { dismissValue ->
                if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                    onDelete()
                    true
                } else false
            }
        ),
        enableDismissFromEndToStart = enableToDelete,
        enableDismissFromStartToEnd = false,
        backgroundContent = { DeletingBackground() }
    ) {
        BaseTextField(
            modifier = modifier,
            text = text,
            label = label,
            onValueChange = onValueChange
        )
    }
}

@Composable
private fun DeletingBackground(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Red.copy(alpha = 0.5f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Delete",
            color = Color.White,
        )

        Icon(
            modifier = Modifier.padding(end = 26.dp),
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.White
        )
    }
}
