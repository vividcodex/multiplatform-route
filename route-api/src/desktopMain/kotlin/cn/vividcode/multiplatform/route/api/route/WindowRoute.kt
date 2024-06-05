package cn.vividcode.multiplatform.route.api.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import cn.vividcode.multiplatform.route.api.config.WindowConfigScopeImpl

/**
 * on desktop platform, you can use open function to open a window.
 */
fun PageScope.open(
	name: String,
	vararg data: Pair<String, *>,
	finish: Boolean = false
) {
	if (!WindowConfigScopeImpl.windowContentMap.containsKey(name) || WindowRouteState.openWindowNames.contains(name)) {
		return
	}
	if (finish) {
		WindowRouteState.openWindowNames -= this.name
	}
	WindowRouteState.openScopeMap[name] = OpenScopeImpl(data.toMap(), this.name)
	WindowRouteState.openWindowNames += name
}

/**
 * on desktop platform, you can use close function to close myself.
 *
 * the application is closed when all forms are closed
 */
fun PageScope.close() {
	if (!WindowConfigScopeImpl.windowContentMap.containsKey(this.name) || !WindowRouteState.openWindowNames.contains(name)) {
		return
	}
	WindowRouteState.openWindowNames -= this.name
	if (WindowRouteState.openWindowNames.isEmpty()) {
		WindowRouteState.exitApplication?.invoke()
	}
}

/**
 * execute when the window is opened
 */
@Composable
fun PageScope.onOpen(scope: OpenScope.() -> Unit) {
	LaunchedEffect(Unit) {
		WindowRouteState.openScopeMap[name]?.scope()
	}
}

internal object WindowRouteState {
	
	var exitApplication: (() -> Unit)? = null
	
	val openWindowNames = mutableStateListOf<String>()
	
	val openScopeMap = mutableMapOf<String, OpenScope>()
}