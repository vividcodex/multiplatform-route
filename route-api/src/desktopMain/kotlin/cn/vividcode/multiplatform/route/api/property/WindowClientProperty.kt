package cn.vividcode.multiplatform.route.api.property

import androidx.compose.ui.window.FrameWindowScope
import cn.vividcode.multiplatform.route.api.platform.Platform

/**
 * full window content on macos.
 */
fun FrameWindowScope.setAppleAwtFullWindowContent(value: Boolean) {
	if (Platform.isMacos) {
		this.window.rootPane.putClientProperty("apple.awt.fullWindowContent", value)
	}
}

/**
 * transparent title bar on macos.
 */
fun FrameWindowScope.setAppleAwtTransparentTitleBar(value: Boolean) {
	if (Platform.isMacos) {
		this.window.rootPane.putClientProperty("apple.awt.transparentTitleBar", value)
	}
}