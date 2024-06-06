package cn.vividcode.multiplatform.route.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import cn.vividcode.multiplatform.route.api.route.PageScope

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContent {
			MobileApp()
		}
		
		handleOnBackPressed()
	}
	
	private fun handleOnBackPressed() {
		// When the page stack is 1, return will launch the program,
		// otherwise return to the previous page and return with the appropriate gesture.
		onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				PageScope.getInstance()?.apply {
					if (this.size == 1) {
						finish()
					} else {
						back()
					}
				}
			}
		})
	}
}