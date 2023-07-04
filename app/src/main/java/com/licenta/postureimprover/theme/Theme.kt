package com.licenta.postureimprover.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = Green80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    primaryContainer = Dune20,

)

private val LightColorScheme = lightColorScheme(
    primary = Green10,
    secondary = Green80,
    tertiary = Dune20,
    primaryContainer= Green80,
    secondaryContainer = Brown90,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PostureImproverTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    val appShapes = Shapes(
        extraSmall = RoundedCornerShape(30.dp),
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(4.dp),
        extraLarge = RoundedCornerShape(4.dp)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = appShapes,
        typography = Typography,
        content = content
    )
}

