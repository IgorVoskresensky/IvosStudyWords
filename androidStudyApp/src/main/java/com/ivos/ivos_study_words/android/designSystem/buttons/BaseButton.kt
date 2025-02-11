package com.ivos.ivos_study_words.android.designSystem.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    label: String = "",
    textSize: TextUnit = 14.sp,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    size: DpSize = DpSize(width = 100.dp, height = 45.dp),
    background: Color = MaterialTheme.colorScheme.background,
    shape: Shape = RoundedCornerShape(16.dp),
    contentPadding: PaddingValues = PaddingValues(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceAround,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    icon: ImageVector? = Icons.Default.Add,
    iconSize: DpSize = DpSize.Unspecified,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    iconContainerSize: DpSize = DpSize.Unspecified,
    iconContainerShape: Shape = RoundedCornerShape(16.dp),
    iconContainerBackground: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
    iconContainerPadding: PaddingValues = PaddingValues(0.dp),
    contentDescription: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .size(size),
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = background,
        ),
        contentPadding = contentPadding,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (enabled) 1f else 0.5f),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
        ) {
            if (label.isNotBlank()) {
                Text(
                    text = label,
                    fontSize = textSize,
                    color = textColor,
                )
            }

            if (icon != null) {
                Box(
                    modifier = Modifier
                        .size(iconContainerSize)
                        .clip(iconContainerShape)
                        .background(iconContainerBackground)
                        .padding(iconContainerPadding)
                ) {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        imageVector = icon,
                        contentDescription = contentDescription,
                        tint = iconColor,
                    )
                }
            }
        }
    }
}
