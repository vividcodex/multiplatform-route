package cn.vividcode.multiplatform.route.api.platform

/**
 * Get different data depending on the platform.
 */
@Suppress("UNCHECKED_CAST")
fun <T> platformValue(
	vararg platformValues: Pair<Platform, T>
): T {
	if (platformValues.isEmpty()) {
		error("The platformValues cannot be empty!!")
	}
	val contains = mutableListOf<Platform>()
	platformValues.forEach {
		if (contains.contains(it.first)) {
			error("You cannot set multiple values for the same platform!")
		}
		contains += it.first
	}
	val platformValuesMap = platformValues.toMap()
	return when {
		platformValuesMap.containsKey(LocalPlatform) -> platformValuesMap[LocalPlatform]
		Platform.isMobile && platformValuesMap.containsKey(Mobile) -> platformValuesMap[Mobile]
		Platform.isDesktop && platformValuesMap.containsKey(Desktop) -> platformValuesMap[Desktop]
		else -> error("No value was found for the platform!")
	} as T
}

val <T> T.android: Pair<Android, T>
	get() = Android to this

val <T> T.ios: Pair<IOS, T>
	get() = IOS to this

val <T> T.web: Pair<Web, T>
	get() = Web to this

val <T> T.windows: Pair<Windows, T>
	get() = Windows to this

val <T> T.macos: Pair<Macos, T>
	get() = Macos to this

val <T> T.linux: Pair<Linux, T>
	get() = Linux to this

val <T> T.mobile: Pair<Mobile, T>
	get() = Mobile to this

val <T> T.desktop: Pair<Desktop, T>
	get() = Desktop to this