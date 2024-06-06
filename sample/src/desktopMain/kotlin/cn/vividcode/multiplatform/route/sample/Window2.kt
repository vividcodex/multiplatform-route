package cn.vividcode.multiplatform.route.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.vividcode.multiplatform.route.api.config.pages
import cn.vividcode.multiplatform.route.api.route.PageScope
import cn.vividcode.multiplatform.route.api.route.close

/**
 * Window2
 */
@Composable
fun Window2() {
	pages(WindowName.WINDOW_2) {
		startup = Window2Route.PAGE_1

		register(Window2Route.PAGE_1) {
			Window2Page1()
		}
	}
}

object Window2Route {
	const val PAGE_1 = "/window2/page1"
}

@Composable
fun PageScope.Window2Page1() {
	Scaffold(
		modifier = Modifier.fillMaxSize(),
	) {
		Column(
			modifier = Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = "窗口可拖动大小",
				fontSize = 20.sp
			)
			Spacer(modifier = Modifier.height(20.dp))
			Button(
				onClick = {
					close()
				}
			) {
				Text("关闭窗口")
			}
		}
	}
}