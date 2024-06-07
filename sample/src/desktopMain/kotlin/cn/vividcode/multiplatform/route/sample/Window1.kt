package cn.vividcode.multiplatform.route.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.vividcode.multiplatform.route.api.compose.WindowDraggableArea
import cn.vividcode.multiplatform.route.api.config.pages
import cn.vividcode.multiplatform.route.api.route.PageScope
import cn.vividcode.multiplatform.route.api.route.open

/**
 * Window1
 */
@Composable
fun Window1() {

	pages(WindowName.WINDOW_1) {

		this.startup = Window1Route.PAGE_1

		register(Window1Route.PAGE_1) {
			Window1Page1()
		}
		register(Window1Route.PAGE_2) {
			Window1Page2()
		}
	}
}

object Window1Route {
	const val PAGE_1 = "/window1/page1"

	const val PAGE_2 = "/window1/page2"
}

/**
 * Window1Page1
 */
@Composable
fun PageScope.Window1Page1() {
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
	) {
		WindowDraggableArea {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(it),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				var value by remember { mutableStateOf("") }

				// onBack
				onBack {
					if (this.resultCode == 1) {
						value = this.data["value"] as String
					}
				}
				OutlinedTextField(
					value = value,
					onValueChange = { value = it }
				)
				Spacer(modifier = Modifier.height(20.dp))
				Button(
					onClick = {
						// route to Window1Page2
						route(Window1Route.PAGE_2, "value" to value)
					}
				) {
					Text("跳转到 Window1Page2")
				}

				Button(
					onClick = {
						// open Window2
						open(WindowName.WINDOW_2)
					}
				) {
					Text("打开 Window2")
				}
			}
		}
	}
}

/**
 * Window1Page2
 */
@Composable
fun PageScope.Window1Page2() {
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(it),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			var value by remember { mutableStateOf("") }

			// onCreate
			onCreate {
				value = this.data["value"] as String
			}

			OutlinedTextField(
				value = value,
				onValueChange = { value = it },
			)
			Spacer(modifier = Modifier.height(20.dp))
			Button(
				onClick = {
					// back
					back("value" to value, resultCode = 1)
				}
			) {
				Text("返回到 Window1Page2")
			}
		}
	}
}