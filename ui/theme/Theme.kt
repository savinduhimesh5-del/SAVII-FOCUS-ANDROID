package com.savii.focus.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val SaviiDarkColorScheme = darkColorScheme(
    primary = SaviiPurple,
    background = SaviiBackground,
    surface = SaviiCard,
    onPrimary = SaviiWhite,
    onBackground = SaviiWhite,
    onSurface = SaviiWhite,
    secondary = SaviiMuted
)

@Composable
fun SaviiFocusTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SaviiDarkColorScheme,
        typography = SaviiTypography,
        content = content
    )
}
