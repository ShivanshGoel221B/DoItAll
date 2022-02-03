package com.gaproductivity.components.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = primaryColor,
    primaryVariant = primaryColor,
    secondary = secondaryColor,
    background = backgroundColorDark,
    surface = surfaceColorDark
)

private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryColor,
    secondary = secondaryColor,
    background = backgroundColorLight,
    surface = surfaceColorLight

    /* Other default colors to override
    onPrimary
    onSecondary
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DoItAllTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
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

val Colors.textColor: Color
get() = if (isLight) textColorDark else textColorLight

val Colors.cardColor: Color
get() = if(isLight) cardColorLight else cardColorDark

val Colors.translucentGray: Color
get() = translucentGrayColor