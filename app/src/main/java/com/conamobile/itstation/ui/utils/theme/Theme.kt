package com.conamobile.itstation.ui.utils.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = GraySurface,
    primaryVariant = GraySurface,
    secondary = GraySurface,
    surface = GraySurface,
)

private val LightColorPalette = lightColors(
    primary = Yellow,
    primaryVariant = Yellow,
    secondary = Yellow,
    surface = Color.White
)

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
//    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}