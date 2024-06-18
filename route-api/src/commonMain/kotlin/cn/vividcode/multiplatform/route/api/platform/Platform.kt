package cn.vividcode.multiplatform.route.api.platform

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

interface Mobile : Platform {
	
	companion object : Platform
}

interface Desktop : Platform {
	
	companion object : Platform
}

data object Android : Mobile

data object IOS : Mobile

data object Macos : Desktop

data object Windows : Desktop

data object Linux : Desktop

data object Web : Platform