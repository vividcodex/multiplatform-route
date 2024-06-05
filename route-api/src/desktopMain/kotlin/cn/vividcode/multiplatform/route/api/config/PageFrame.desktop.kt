package cn.vividcode.multiplatform.route.api.config

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.vividcode.multiplatform.route.api.route.PageScope

/**
 * page frame on desktop.
 */
@Composable
internal actual fun PageScope.PageFrame(
	content: @Composable PageScope.() -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
	) {
		content()
	}
}