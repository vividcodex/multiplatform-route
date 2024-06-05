package cn.vividcode.multiplatform.route.api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cn.vividcode.multiplatform.route.api.animation.FadeRouteAnimation
import cn.vividcode.multiplatform.route.api.animation.RouteAnimation
import cn.vividcode.multiplatform.route.api.theme.color.ColorMode
import cn.vividcode.multiplatform.route.api.theme.color.DefaultColorScheme
import cn.vividcode.multiplatform.route.api.theme.color.VividCodeColorScheme

/**
 * RouteConfig, you can configure colorMode, colorScheme and routeAnimation.
 */
object RouteConfig {
	
	/**
	 * 颜色模式
	 */
	var colorMode: ColorMode by mutableStateOf(ColorMode.System)
	
	/**
	 * 颜色主题
	 */
	var colorScheme: VividCodeColorScheme by mutableStateOf(DefaultColorScheme)
	
	/**
	 * 路由动画
	 */
	var routeAnimation: RouteAnimation by mutableStateOf(FadeRouteAnimation)
}