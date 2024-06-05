package cn.vividcode.multiplatform.route.api.animation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset

/**
 * Horizontal motion animation.
 */
object SlideRouteAnimation : RouteAnimation {
	
	override val onCreateEnter = fadeIn(initialAlpha = 0.5F) + slideIn { IntOffset(it.width, 0) }
	
	override val onCreateExit = fadeOut(targetAlpha = 0.5F) + slideOut { IntOffset(-it.width, 0) }
	
	override val onBackEnter = fadeIn(initialAlpha = 0.5F) + slideIn { IntOffset(-it.width, 0) }
	
	override val onBackExit = fadeOut(targetAlpha = 0.5F) + slideOut { IntOffset(it.width, 0) }
}

/**
 * Fade in and out animation.
 */
object FadeRouteAnimation : RouteAnimation {
	
	override val onCreateEnter = fadeIn(initialAlpha = 0F) + slideIn { IntOffset(it.width, 0) }
	
	override val onCreateExit = fadeOut(targetAlpha = 0F) + slideOut { IntOffset(-it.width / 4, 0) }
	
	override val onBackEnter = fadeIn(initialAlpha = 0F) + slideIn { IntOffset(-it.width / 4, 0) }
	
	override val onBackExit = fadeOut(targetAlpha = 0F) + slideOut { IntOffset(it.width, 0) }
}