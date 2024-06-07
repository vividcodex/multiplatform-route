package cn.vividcode.multiplatform.route.api.platform

import androidx.compose.ui.Modifier

/**
 * Get different data depending on the platform.
 */
inline fun <reified T> platform(
	mobile: T? = null,
	desktop: T? = null,
	ios: T? = mobile,
	android: T? = mobile,
	macos: T? = desktop,
	window: T? = desktop,
	linux: T? = desktop,
): T = when {
	Platform.isIOS -> ios
	Platform.isAndroid -> android
	Platform.isMacos -> macos
	Platform.isWindows -> window
	Platform.isLinux -> linux
	else -> error("Unsupported platforms.")
}!!

/**
 * Modifiers for different platforms depending on the platform
 */
fun Modifier.platform(vararg platforms: Platform, modifier: Modifier.() -> Modifier): Modifier {
	if (platforms.isEmpty()) return this
	if (LocalPlatform in platforms) {
		return this.modifier()
	}
	return if ((platforms.contains(Mobile) && Platform.isMobile) || (platforms.contains(Desktop) && Platform.isDesktop)) {
		this.modifier()
	} else {
		this
	}
}