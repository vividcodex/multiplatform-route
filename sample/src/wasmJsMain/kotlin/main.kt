import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import cn.vividcode.multiplatform.route.sample.MobileApp
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
	ComposeViewport(document.body!!) {
		MobileApp()
	}
}