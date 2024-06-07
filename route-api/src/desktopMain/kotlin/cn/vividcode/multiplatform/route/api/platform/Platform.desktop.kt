package cn.vividcode.multiplatform.route.api.platform

/**
 * DesktopPlatform has Macos, Windows and Linux.
 */
internal actual val LocalPlatform: Platform by lazy {
	val osName = System.getProperty("os.name").lowercase().replace(" ", "")
	when {
		osName.contains("macos") -> Macos
		osName.contains("windows") -> Windows
		osName.contains("linux") -> Linux
		else -> error("Unsupported desktop operating system.")
	}
}