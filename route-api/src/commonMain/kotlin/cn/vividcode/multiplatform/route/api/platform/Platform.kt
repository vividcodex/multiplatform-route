package cn.vividcode.multiplatform.route.api.platform

import androidx.compose.ui.Modifier

/**
 * currently running platform.
 */
internal expect val LocalPlatform: Platform

sealed interface Platform {
	
	companion object {
		
		val isAndroid = LocalPlatform == Android
		
		val isIOS = LocalPlatform == IOS
		
		val isMacos = LocalPlatform == Macos
		
		val isWindows = LocalPlatform == Windows
		
		val isLinux = LocalPlatform == Linux
		
		val isMobile = LocalPlatform is Mobile
		
		val isDesktop = LocalPlatform is Desktop
		
		val isWeb = LocalPlatform is Web
	}
}

sealed interface Mobile : Platform {
	
	companion object : Mobile
}

sealed interface Desktop : Platform {
	
	companion object : Desktop
}

data object Android : Mobile

data object IOS : Mobile

data object Macos : Desktop

data object Windows : Desktop

data object Linux : Desktop

data object Web : Platform

/**
 * Modifiers for different platforms depending on the platform
 */
fun Modifier.platform(
	vararg platforms: Platform,
	modifier: Modifier.() -> Modifier
): Modifier {
	if (platforms.isEmpty()) return this
	return if (
		platforms.any { it == LocalPlatform }
		|| (Mobile in platforms && LocalPlatform is Mobile)
		|| (Desktop in platforms && LocalPlatform is Desktop)
	) this.modifier() else this
}