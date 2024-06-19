package cn.vividcode.multiplatform.route.api.platform

/**
 * Get different data depending on the platform.
 */
@Suppress("UNCHECKED_CAST")
fun <T> platformValue(
	vararg platformValues: Pair<Platform, T>
): T {
	check(platformValues.isNotEmpty()) {
		"The platformValues cannot be empty!"
	}
	val platformValuesMap = platformValues.toMap()
	check(platformValues.size == platformValuesMap.size) {
		"You cannot set multiple values for the same platform!"
	}
	return when {
		platformValuesMap.containsKey(LocalPlatform) -> platformValuesMap[LocalPlatform]
		Platform.isMobile && platformValuesMap.containsKey(Mobile) -> platformValuesMap[Mobile]
		Platform.isDesktop && platformValuesMap.containsKey(Desktop) -> platformValuesMap[Desktop]
		else -> error("No value was found for the platform!")
	} as T
}

inline val <T> T.android: Pair<Android, T>
	get() = Android to this

inline val <T> T.ios: Pair<IOS, T>
	get() = IOS to this

inline val <T> T.web: Pair<Web, T>
	get() = Web to this

inline val <T> T.windows: Pair<Windows, T>
	get() = Windows to this

inline val <T> T.macos: Pair<Macos, T>
	get() = Macos to this

inline val <T> T.linux: Pair<Linux, T>
	get() = Linux to this

inline val <T> T.mobile: Pair<Mobile, T>
	get() = Mobile to this

inline val <T> T.desktop: Pair<Desktop, T>
	get() = Desktop to this