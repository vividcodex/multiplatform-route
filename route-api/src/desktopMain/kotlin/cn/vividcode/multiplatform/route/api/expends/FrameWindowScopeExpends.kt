package cn.vividcode.multiplatform.route.api.expends

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.FrameWindowScope
import cn.vividcode.multiplatform.route.api.platform.Platform
import java.awt.Dimension

/**
 * full window content on macos.
 */
internal fun FrameWindowScope.setAppleAwtFullWindowContent(value: Boolean) {
	if (Platform.isMacos) {
		this.window.rootPane.putClientProperty("apple.awt.fullWindowContent", value)
	}
}

/**
 * transparent title bar on macos.
 */
internal fun FrameWindowScope.setAppleAwtTransparentTitleBar(value: Boolean) {
	if (Platform.isMacos) {
		this.window.rootPane.putClientProperty("apple.awt.transparentTitleBar", value)
	}
}

/**
 * set the minimum width and height of the window.
 */
internal fun FrameWindowScope.setMinWindowSize(width: Dp, height: Dp) {
	this.window.minimumSize = Dimension(width.value.toInt(), height.value.toInt())
}