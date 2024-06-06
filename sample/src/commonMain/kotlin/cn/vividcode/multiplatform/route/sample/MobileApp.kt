package cn.vividcode.multiplatform.route.sample

import androidx.compose.runtime.Composable
import cn.vividcode.multiplatform.route.api.config.pages

/**
 * MobileApp
 */
@Composable
fun MobileApp() {
	
	// pages
	pages {
		
		// startup
		this.startup = MobileRoute.PAGE_1
		
		// register page1
		register(MobileRoute.PAGE_1) {
			Page1()
		}
		
		// register page2
		register(MobileRoute.PAGE_2) {
			Page2()
		}
	}
}

object MobileRoute {
	
	const val PAGE_1 = "/page1"
	
	const val PAGE_2 = "/page2"
}