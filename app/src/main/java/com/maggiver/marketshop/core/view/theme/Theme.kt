package com.maggiver.marketshop.core.view.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)


@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)
@Immutable
data class ExtendedColorScheme(
    val customColor1: ColorFamily,
    val customColor2: ColorFamily,
    val customColor3: ColorFamily,
)

val extendedLight = ExtendedColorScheme(
    customColor1 = ColorFamily(
        customColor1Light,
        onCustomColor1Light,
        customColor1ContainerLight,
        onCustomColor1ContainerLight,
    ),
    customColor2 = ColorFamily(
        customColor2Light,
        onCustomColor2Light,
        customColor2ContainerLight,
        onCustomColor2ContainerLight,
    ),
    customColor3 = ColorFamily(
        customColor3Light,
        onCustomColor3Light,
        customColor3ContainerLight,
        onCustomColor3ContainerLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    customColor1 = ColorFamily(
        customColor1Dark,
        onCustomColor1Dark,
        customColor1ContainerDark,
        onCustomColor1ContainerDark,
    ),
    customColor2 = ColorFamily(
        customColor2Dark,
        onCustomColor2Dark,
        customColor2ContainerDark,
        onCustomColor2ContainerDark,
    ),
    customColor3 = ColorFamily(
        customColor3Dark,
        onCustomColor3Dark,
        customColor3ContainerDark,
        onCustomColor3ContainerDark,
    ),
)

val LocalExtendedColorScheme = staticCompositionLocalOf {
    // Definimos un valor por defecto (normalmente el esquema claro)
    extendedLight
}

@Composable
fun MarketShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // 1. Lógica para el esquema de color principal (Estándar o Dinámico)
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkScheme
        else -> lightScheme
    }

    // 2. Elegir la versión de colores extendidos (Personalizados)
    val extendedColorScheme = if (darkTheme) extendedDark else extendedLight

    // 3. Un solo MaterialTheme envolviendo todo
    CompositionLocalProvider(LocalExtendedColorScheme provides extendedColorScheme) {
        MaterialTheme(
            colorScheme = colorScheme, // Usa la variable calculada arriba
            typography = AppTypography,
            content = content
        )
    }
}