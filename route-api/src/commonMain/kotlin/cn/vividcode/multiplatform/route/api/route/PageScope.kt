package cn.vividcode.multiplatform.route.api.route

import androidx.compose.runtime.*
import cn.vividcode.multiplatform.route.api.config.PageConfigScope
import cn.vividcode.multiplatform.route.api.config.PageConfigScopeImpl
import kotlin.math.min

/**
 * PageScope is used to control page jump, data transfer and other tasks,
 *
 * in use you need to create a PageScope extension method
 */
sealed interface PageScope {

	companion object {

		internal var canRoute = true
	}

	val name: String

	val routeMap: Map<String, @Composable PageScope.() -> Unit>

	var currentPageRoute: String

	var currentPageRouteStatus: PageRouteStatus

	fun route(
		route: String,
		vararg data: Pair<String, *>,
		finish: Boolean = false,
		finishAll: Boolean = false
	)

	fun back(
		vararg data: Pair<String, *>,
		resultCode: Int = ResultCode.BACK,
		depth: Int = 1,
		toFirst: Boolean = false
	)

	@Composable
	fun onCreate(
		scope: CreateScope.() -> Unit
	)

	@Composable
	fun onBack(
		scope: BackScope.() -> Unit
	)

	object ResultCode {

		const val BACK = 0
	}
}

internal class PageScopeImpl(
	launch: String,
	override val name: String,
	override val routeMap: Map<String, @Composable PageScope.() -> Unit>
) : PageScope {

	override var currentPageRoute by mutableStateOf(launch)

	override var currentPageRouteStatus: PageRouteStatus = PageRouteStatus.OnCreate

	private val pageRouteStack = mutableListOf(launch)

	private val createScopeCache = mutableMapOf<String, CreateScope>()

	private val backScopeCache = mutableMapOf<String, BackScope>()

	override fun route(route: String, vararg data: Pair<String, *>, finish: Boolean, finishAll: Boolean) {
		if (!PageScope.canRoute) return
		if (!routeMap.keys.contains(route)) {
			error("$route not found.")
		}
		this.currentPageRouteStatus = PageRouteStatus.OnCreate
		this.createScopeCache[route] = CreateScopeImpl(data.toMap(), currentPageRoute)
		if (finishAll) {
			this.pageRouteStack.clear()
		} else if (finish) {
			this.pageRouteStack.removeLast()
		}
		this.pageRouteStack += route
		this.currentPageRoute = route
	}

	override fun back(vararg data: Pair<String, *>, resultCode: Int, depth: Int, toFirst: Boolean) {
		if (!PageScope.canRoute) return
		if (depth < 1) error("depth must be not < 1.")
		this.currentPageRouteStatus = PageRouteStatus.OnBack
		repeat(min(pageRouteStack.size - 1, if (toFirst) Int.MAX_VALUE else depth)) {
			this.pageRouteStack.removeLast()
		}
		val route = pageRouteStack.last()
		this.backScopeCache[route] = BackScopeImpl(data.toMap(), resultCode, this.currentPageRoute)
		this.currentPageRoute = route
	}

	@Composable
	override fun onCreate(
		scope: CreateScope.() -> Unit
	) {
		LaunchedEffect(Unit) {
			if (currentPageRouteStatus == PageRouteStatus.OnCreate) {
				createScopeCache[currentPageRoute]?.scope()
			}
		}
	}

	@Composable
	override fun onBack(
		scope: BackScope.() -> Unit
	) {
		LaunchedEffect(Unit) {
			if (currentPageRouteStatus == PageRouteStatus.OnBack) {
				backScopeCache[currentPageRoute]?.scope()
			}
		}
	}
}

internal fun getPageScope(
	name: String,
	config: PageConfigScope.() -> Unit
): PageScope {
	return PageConfigScopeImpl().apply(config).let {
		if (it.startup == null) {
			error("init not configuration.")
		}
		pageScopeCache.getOrPut(name) {
			PageScopeImpl(it.startup!!, name, it.pageContentMap)
		}
	}
}

internal val pageScopeCache = mutableMapOf<String, PageScope>()