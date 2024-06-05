package cn.vividcode.multiplatform.route.api.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * PlatformTheme on android.
 */
@Composable
internal actual fun PlatformTheme(
	colorScheme: ColorScheme,
	darkTheme: Boolean,
	content: @Composable () -> Unit
) {
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			val window = (view.context as Activity).window.apply {
				this.navigationBarColor = Color.Transparent.toArgb()
				this.statusBarColor = Color.Transparent.toArgb()
			}
			WindowCompat.getInsetsController(window, view).apply {
				this.isAppearanceLightStatusBars = !darkTheme
				this.isAppearanceLightNavigationBars = !darkTheme
			}
			WindowCompat.setDecorFitsSystemWindows(window, false)
		}
	}
	content()
}