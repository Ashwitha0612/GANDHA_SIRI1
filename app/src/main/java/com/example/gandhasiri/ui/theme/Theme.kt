package com.example.gandhasiri.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val WoodColorScheme = lightColorScheme(
    primary = SandalBrown,
    secondary = WoodLight,
    tertiary = LeafGreen,
    background = CreamBackground,
    surface = SurfaceWood,
    onPrimary = SurfaceWood,
    onSecondary = TextDark,
    onTertiary = SurfaceWood,
    onBackground = TextDark,
    onSurface = TextDark,
    error = AlertRed
)

@Composable
fun GandhaSiriTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WoodColorScheme,
        typography = Typography,
        content = content
    )
}
