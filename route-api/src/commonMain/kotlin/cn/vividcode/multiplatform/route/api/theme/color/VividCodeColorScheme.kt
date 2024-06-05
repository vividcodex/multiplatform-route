package cn.vividcode.multiplatform.route.api.theme.color

import androidx.compose.material3.ColorScheme

/**
 * Implement the VividCodeColorScheme to implement your theme
 */
interface VividCodeColorScheme {
	
	/**
	 * 亮色方案
	 */
	val lightColorScheme: ColorScheme
	
	/**
	 * 暗色方案
	 */
	val darkColorScheme: ColorScheme
}