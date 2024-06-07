package cn.vividcode.multiplatform.route.api.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import cn.vividcode.multiplatform.route.api.compose.LocalFrameWindowScope
import cn.vividcode.multiplatform.route.api.expends.setAppleAwtFullWindowContent
import cn.vividcode.multiplatform.route.api.expends.setAppleAwtTransparentTitleBar
import cn.vividcode.multiplatform.route.api.expends.setMinWindowSize
import cn.vividcode.multiplatform.route.api.route.PageScope
import cn.vividcode.multiplatform.route.api.route.WindowRouteState
import cn.vividcode.multiplatform.route.api.route.close
import cn.vividcode.multiplatform.route.api.theme.VividCodeTheme
import java.awt.GraphicsEnvironment
import java.awt.MouseInfo
import java.awt.Point
import java.awt.Rectangle

/**
 * you can configure the window name size title and other information.
 */
@Composable
fun ApplicationScope.windows(
	config: WindowConfigScope.() -> Unit
) {
	val scope = getWindowConfigScope(config, this::exitApplication)
	scope.windowContentMap.forEach { (name, config) ->
		val visible = WindowRouteState.openWindowNames.contains(name)
		Window(
			onCloseRequest = {
				PageScope.pageScopeCache[name]?.close()
			},
			state = getWindowState(name, config.size),
			visible = visible,
			title = config.title,
			icon = config.icon,
			resizable = config.size is ResizableWindowSize
		) {
			if (!visible) return@Window
			LaunchedEffect(Unit) {
				setAppleAwtFullWindowContent(true)
				setAppleAwtTransparentTitleBar(true)
				if (config.size is ResizableWindowSize) {
					val size = config.size
					setMinWindowSize(size.minWidth, size.minHeight)
				}
			}
			VividCodeTheme {
				CompositionLocalProvider(LocalFrameWindowScope provides this) {
					config.content(this)
				}
			}
		}
	}
}

private fun getWindowConfigScope(
	config: WindowConfigScope.() -> Unit,
	exitApplication: () -> Unit
): WindowConfigScopeImpl {
	if (WindowConfigScopeImpl.startup == null) {
		WindowRouteState.exitApplication = exitApplication
		WindowConfigScopeImpl.config()
		if (WindowConfigScopeImpl.startup != null) {
			WindowRouteState.openWindowNames += WindowConfigScopeImpl.startup!!
		}
	}
	return WindowConfigScopeImpl
}

private val WindowStateMap = mutableMapOf<String, WindowState>()

private val GraphicsDevices by lazy {
	GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices
}

private val point by lazy { MouseInfo.getPointerInfo().location }

@Composable
private fun getWindowState(
	name: String,
	size: WindowSize,
): WindowState {
	return WindowStateMap.getOrPut(name) {
		var position: WindowPosition? = null
		GraphicsDevices.forEach {
			val deviceBounds = it.defaultConfiguration.bounds
			if (mousePointIsInDeviceBounds(deviceBounds, point)) {
				position = WindowPosition.Absolute(
					x = (deviceBounds.x + (deviceBounds.width - size.width.value) / 2F).dp,
					y = (deviceBounds.y + (deviceBounds.height - size.height.value) / 2F).dp
				)
				return@forEach
			}
		}
		rememberWindowState(
			position = position ?: WindowPosition.Aligned(Alignment.Center),
			size = DpSize(size.width, size.height)
		)
	}
}

/**
 * 光标是否在设备屏幕内
 */
private fun mousePointIsInDeviceBounds(deviceBounds: Rectangle, mousePoint: Point): Boolean {
	return mousePoint.x >= deviceBounds.x
		&& mousePoint.x <= deviceBounds.x + deviceBounds.width
		&& mousePoint.y >= deviceBounds.y
		&& mousePoint.y <= deviceBounds.y + deviceBounds.height
}