package cn.vividcode.multiplatform.route.api.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cn.vividcode.multiplatform.route.api.RouteConfig
import cn.vividcode.multiplatform.route.api.limit.RouteAllowState
import cn.vividcode.multiplatform.route.api.theme.color.ColorMode

/**
 * vividcode theme.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun VividCodeTheme(
	content: @Composable () -> Unit
) {
	val darkTheme = when (RouteConfig.colorMode) {
		ColorMode.Light -> false
		ColorMode.Dark -> true
		ColorMode.System -> isSystemInDarkTheme()
	}
	val colorScheme = RouteConfig.colorScheme.let {
		if (darkTheme) it.darkColorScheme else it.lightColorScheme
	}
	PlatformTheme(colorScheme, darkTheme) {
		AnimatedContent(
			targetState = colorScheme,
			transitionSpec = { TransitionSpec },
		) {
			RouteAllowState.isAllowModificationColorMode = !this.transition.isRunning
			MaterialTheme(
				colorScheme = it,
				content = content
			)
		}
	}
}

private val TransitionSpec = fadeIn(animationSpec = tween(durationMillis = 400)).togetherWith(
	exit = fadeOut(animationSpec = tween(durationMillis = 400))
)