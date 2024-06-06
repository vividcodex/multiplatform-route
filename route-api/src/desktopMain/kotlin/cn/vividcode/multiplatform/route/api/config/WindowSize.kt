package cn.vividcode.multiplatform.route.api.config

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * window size.
 */
sealed interface WindowSize {
	
	val width: Dp
	
	val height: Dp
	
	companion object {
		
		/**
		 * fixed.
		 */
		fun fixed(width: Dp, height: Dp): WindowSize = FixedWindowSize(width, height)
		
		/**
		 * resizable.
		 */
		fun resizable(
			width: Dp,
			height: Dp,
			minWidth: Dp = 0.dp,
			minHeight: Dp = 0.dp
		): WindowSize = ResizableWindowSize(width, height, minWidth, minHeight)
	}
}

/**
 * fixed size.
 */
internal data class FixedWindowSize(
	override val width: Dp,
	override val height: Dp
) : WindowSize

/**
 * resizable.
 */
internal data class ResizableWindowSize(
	override val width: Dp,
	override val height: Dp,
	val minWidth: Dp,
	val minHeight: Dp,
) : WindowSize