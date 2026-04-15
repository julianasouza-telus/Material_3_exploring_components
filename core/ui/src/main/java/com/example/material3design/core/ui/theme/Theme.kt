package com.example.material3design.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary      = TaskFlowPrimary,
    onPrimary    = Color.White,
    secondary    = TaskFlowSecondary,
    onSecondary  = Color(0xFF001F2A),
    tertiary     = TaskFlowSuccess,
    onTertiary   = Color.White,
    error        = TaskFlowError,
    onError      = Color.White,
    background   = TaskFlowBackground,
    onBackground = Color(0xFF1C1B1F),
    surface      = Color(0xFFFFFFFF),
    onSurface    = Color(0xFF1C1B1F),
)

private val DarkColorScheme = darkColorScheme(
    primary      = TaskFlowPrimaryDark,
    onPrimary    = Color(0xFF381E72),
    secondary    = TaskFlowSecondaryDark,
    onSecondary  = Color(0xFF001F2A),
    tertiary     = TaskFlowSuccessDark,
    onTertiary   = Color(0xFF1B5E20),
    error        = TaskFlowErrorDark,
    onError      = Color(0xFF690005),
    background   = TaskFlowBackgroundDark,
    onBackground = Color(0xFFE6E1E5),
    surface      = Color(0xFF2B2930),
    onSurface    = Color(0xFFE6E1E5),
)

@Composable
fun Material3DesignTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
