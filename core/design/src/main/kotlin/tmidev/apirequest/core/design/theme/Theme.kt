package tmidev.apirequest.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import tmidev.apirequest.core.design.util.isCompatibleWithDynamicColors

/**
 * App default theme.
 *
 * @param useDarkTheme when the theme should have dark colors. Default is [isSystemInDarkTheme].
 * @param useDynamicColors when the color scheme should use colors based on device wallpaper,
 * this will only be applied on API 31 and up. Default is true.
 * @param content the content for this theme.
 */
@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColors: Boolean = true,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colorScheme = when {
        isCompatibleWithDynamicColors() && useDynamicColors -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context = context)
            else dynamicLightColorScheme(context = context)
        }
        useDarkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = colorScheme.background.luminance() > 0.5
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorScheme.background,
                contentColor = colorScheme.onBackground,
                content = content
            )
        }
    )
}