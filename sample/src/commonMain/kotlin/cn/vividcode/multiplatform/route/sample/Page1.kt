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
import cn.vividcode.multiplatform.route.api.route.PageScope

/**
 * Page1
 */
@Composable
fun PageScope.Page1() {
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
					// route to page2
					route(MobileRoute.PAGE_2, "value" to value)
				}
			) {
				Text("跳转到 Page2")
			}
		}
	}
}