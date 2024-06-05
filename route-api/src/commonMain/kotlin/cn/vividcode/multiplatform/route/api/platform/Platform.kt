package cn.vividcode.multiplatform.route.api.platform

/**
 * currently running platform.
 */
expect val LocalPlatform: Platform

interface Platform {
	
	companion object {
		
		val isAndroid = LocalPlatform == MobilePlatform.Android
		
		val isIOS = LocalPlatform == MobilePlatform.IOS
		
		val isMacos = LocalPlatform == DesktopPlatform.Macos
		
		val isWindows = LocalPlatform == DesktopPlatform.Windows
		
		val isLinux = LocalPlatform == DesktopPlatform.Linux
		
		val isMobile = LocalPlatform is MobilePlatform
		
		val isDesktop = LocalPlatform is DesktopPlatform
	}
}

internal enum class MobilePlatform : Platform {
	Android,
	IOS
}

internal enum class DesktopPlatform : Platform {
	Macos,
	Windows,
	Linux
}