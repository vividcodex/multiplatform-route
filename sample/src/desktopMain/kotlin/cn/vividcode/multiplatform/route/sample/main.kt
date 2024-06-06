package cn.vividcode.multiplatform.route.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.application
import cn.vividcode.multiplatform.route.api.config.WindowSize
import cn.vividcode.multiplatform.route.api.config.windows
import java.awt.Dimension

fun main() = application {
	DesktopApp()
}

@Composable
fun ApplicationScope.DesktopApp() {
	windows {
		this.startup = WindowName.WINDOW_1
		
		register(
			name = WindowName.WINDOW_1,
			size = WindowSize.fixed(300.dp, 500.dp)
		) {
			Window1()
		}
		
		register(
			name = WindowName.WINDOW_2,
			size = WindowSize.resizable(400.dp, 500.dp, 400.dp, 500.dp),
		) {
			Window2()
		}
	}
}

object WindowName {
	
	const val WINDOW_1 = "window1"
	
	const val WINDOW_2 = "window2"
}