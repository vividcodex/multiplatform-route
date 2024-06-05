package cn.vividcode.multiplatform.route.api.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.FrameWindowScope

/**
 * window configure scope.
 *
 * you can set the initialization window and register all the window information.
 */
sealed interface WindowConfigScope {
	
	/**
	 * startup window.
	 */
	var startup: String?
	
	/**
	 * register.
	 */
	fun register(
		name: String,
		size: DpSize,
		title: String = "",
		icon: Painter? = null,
		resizable: Boolean = true,
		content: @Composable FrameWindowScope.() -> Unit
	)
}

internal data object WindowConfigScopeImpl : WindowConfigScope {
	
	override var startup: String? = null
		set(value) {
			if (field == null && value != null && nameRegex.matches(value)) {
				field = value
			}
		}
	
	val windowContentMap = mutableMapOf<String, Config>()
	
	private val nameRegex = "^[a-zA-Z]+$".toRegex()
	
	/**
	 * register
	 */
	override fun register(
		name: String,
		size: DpSize,
		title: String,
		icon: Painter?,
		resizable: Boolean,
		content: @Composable FrameWindowScope.() -> Unit
	) {
		if (!this.windowContentMap.containsKey(name) && nameRegex.matches(name)) {
			this.windowContentMap[name] = Config(size, title, icon, resizable, content)
		}
	}
	
	internal data class Config(
		val size: DpSize,
		val title: String,
		val icon: Painter?,
		val resizable: Boolean,
		val content: @Composable FrameWindowScope.() -> Unit
	)
}