package com.food.zone.presentation.component.auth

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SkipButton(
    buttonContinerColor: Color,
    buttonContent: Color,
    titleColor: Color,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonContinerColor,
            contentColor = buttonContent
        ),
        modifier = modifier
    ) {
        Text(
            text = title,
            color = titleColor
        )
    }
}