package cn.vividcode.multiplatform.route.api.config

import androidx.compose.runtime.Composable
import cn.vividcode.multiplatform.route.api.route.PageScope

/**
 * page configuration scope.
 */
sealed interface PageConfigScope {

	/**
	 * startup route.
	 */
	var startup: String?

	/**
	 * register a page route.
	 */
	fun register(route: String, content: @Composable PageScope.() -> Unit)
}

internal class PageConfigScopeImpl : PageConfigScope {

	override var startup: String? = null
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
		private val routeRegex = "^(/[a-zA-Z0-9]+)+$".toRegex()
	}

	/**
	 * register a page route
	 */
	override fun register(route: String, content: @Composable PageScope.() -> Unit) {
		if (routeRegex.matches(route)) {
			this.pageContentMap[route] = content
		}
	}
}