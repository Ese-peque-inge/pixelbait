package com.pixelbait.app.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PixelBaitDarkColorScheme = darkColorScheme(
    primary = PbViolet,
    onPrimary = PbTextPrimary,
    secondary = PbNavy,
    onSecondary = PbTextPrimary,
    background = PbBlack,
    onBackground = PbTextPrimary,
    surface = PbSurface,
    onSurface = PbTextPrimary,
    surfaceVariant = PbSurfaceElevated,
    onSurfaceVariant = PbTextSecondary,
    error = PbError,
    onError = PbTextPrimary,
    outline = PbBorder
)

/**
 * Pixel Bait usa siempre tema oscuro absoluto (#000000), independientemente
 * del tema del sistema, tal como se define en el mockup de Ajustes.
 */
@Composable
fun PixelBaitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PixelBaitDarkColorScheme,
        typography = PixelBaitTypography,
        content = content
    )
}
