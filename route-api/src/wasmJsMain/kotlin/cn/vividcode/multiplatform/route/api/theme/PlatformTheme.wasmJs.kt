package cn.vividcode.multiplatform.route.api.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

/**
 * platform theme.
 */
@Composable
internal actual fun PlatformTheme(
	colorScheme: ColorScheme,
	darkTheme: Boolean,
	content: @Composable () -> Unit
) {
	content()
}