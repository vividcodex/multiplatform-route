package cn.vividcode.multiplatform.route.api.config

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cn.vividcode.multiplatform.route.api.RouteConfig
import cn.vividcode.multiplatform.route.api.route.PageRouteStatus
import cn.vividcode.multiplatform.route.api.route.PageScope
import cn.vividcode.multiplatform.route.api.route.getPageScope

/**
 * You can configure page routes and components in pages,
 *
 * which will be controlled by the methods provided by PageScope
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun pages(
	name: String = "",
	config: PageConfigScope.() -> Unit
) {
	val pageScope = getPageScope(name, config)
	pageScope.PageFrame {
		val (enter, exit) = when (currentPageRouteStatus) {
			PageRouteStatus.OnCreate -> RouteConfig.routeAnimation.let {
				it.onCreateEnter to it.onCreateExit
			}
			
			PageRouteStatus.OnBack -> RouteConfig.routeAnimation.let {
				it.onBackEnter to it.onBackExit
			}
		}
		routeMap.forEach { (pageRoute, content) ->
			AnimatedVisibility(
				visible = pageRoute == currentPageRoute,
				modifier = Modifier
					.fillMaxSize(),
				enter = enter,
				exit = exit
			) {
				PageScope.canRoute = !this.transition.isRunning
				content()
			}
		}
	}
}