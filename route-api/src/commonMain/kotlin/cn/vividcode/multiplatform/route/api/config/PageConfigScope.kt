package cn.vividcode.multiplatform.route.api.config

import androidx.compose.runtime.Composable
import cn.vividcode.multiplatform.route.api.route.PageScope

/**
 * page configuration scope.
 */
sealed interface PageConfigScope {
	
	/**
	 * initiating route.
	 */
	var init: String?
	
	/**
	 * register a page route.
	 */
	fun register(route: String, content: @Composable PageScope.() -> Unit)
}

internal class PageConfigScopeImpl : PageConfigScope {

	override var init: String? = null
		set(value) {
			if (field == null && value != null && routeRegex.matches(value)) {
				field = value
			}
		}

	val pageContentMap = mutableMapOf<String, @Composable PageScope.() -> Unit>()

	private companion object {
		
		/**
		 * page route regex.
		 */
		private val routeRegex = "^(/[a-zA-Z]+)+$".toRegex()
	}

	/**
	 * register a page route
	 */
	override fun register(route: String, content: @Composable PageScope.() -> Unit) {
		if (!this.pageContentMap.containsKey(route) && routeRegex.matches(route)) {
			this.pageContentMap[route] = content
		}
	}
}