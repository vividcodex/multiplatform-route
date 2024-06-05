package cn.vividcode.multiplatform.route.api.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition

/**
 * RouteAnimation
 *
 * You can implement it yourself or use the two schemes provided by default.
 *
 * SlideRouteAnimation and FadeRouteAnimation(default)
 *
 * You can be in RouteConfig.routeAnimation in configuration.
 */
interface RouteAnimation {

	val onCreateEnter: EnterTransition
	
	val onCreateExit: ExitTransition
	
	val onBackEnter: EnterTransition
	
	val onBackExit: ExitTransition
}