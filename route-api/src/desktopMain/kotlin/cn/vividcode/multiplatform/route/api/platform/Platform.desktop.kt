package cn.vividcode.multiplatform.route.api.platform

/**
 * DesktopPlatform has Macos, Windows and Linux.
 */
actual val LocalPlatform: Platform by lazy {
	val osName = System.getProperty("os.name").lowercase().replace(" ", "")
	when {
		osName.contains("macos") -> DesktopPlatform.Macos
		osName.contains("windows") -> DesktopPlatform.Windows
		osName.contains("linux") -> DesktopPlatform.Linux
		else -> error("Unsupported desktop operating system.")
	}
}