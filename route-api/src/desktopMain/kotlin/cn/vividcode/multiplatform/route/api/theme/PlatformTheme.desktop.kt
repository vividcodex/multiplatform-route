package cn.vividcode.multiplatform.route.api.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * platform theme.
 */
@Composable
internal actual fun PlatformTheme(
	colorScheme: ColorScheme,
	darkTheme: Boolean,
	content: @Composable () -> Unit
) {
	MaterialTheme(
		colorScheme = colorScheme
	) {
		Surface(
			modifier = Modifier
				.fillMaxSize(),
			shape = RoundedCornerShape(10.dp),
			shadowElevation = 10.dp,
			color = MaterialTheme.colorScheme.surface,
			content = content
		)
	}
}