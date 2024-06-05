package cn.vividcode.multiplatform.route.api.compose

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope

/**
 * window draggable area
 */
@Composable
fun WindowDraggableArea(
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit = {}
) {
	LocalFrameWindowScope.current?.WindowDraggableArea(
		modifier = modifier,
		content = content
	)
}

internal val LocalFrameWindowScope = compositionLocalOf<FrameWindowScope?> { null }