package cn.vividcode.multiplatform.route.api.config

import androidx.compose.runtime.Composable
import cn.vividcode.multiplatform.route.api.route.PageScope

/**
 * methods for adapting to different platforms
 */
@Composable
internal expect fun PageScope.PageFrame(
	content: @Composable PageScope.() -> Unit
)