package cn.vividcode.multiplatform.route.api.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

/**
 * default color scheme.
 */
data object DefaultColorScheme : VividCodeColorScheme {
	
	override val lightColorScheme: ColorScheme by lazy {
		lightColorScheme(
			primary = LightDefaultColor.primary,
			onPrimary = LightDefaultColor.onPrimary,
			primaryContainer = LightDefaultColor.primaryContainer,
			onPrimaryContainer = LightDefaultColor.onPrimaryContainer,
			inversePrimary = LightDefaultColor.inversePrimary,
			secondary = LightDefaultColor.secondary,
			onSecondary = LightDefaultColor.onSecondary,
			secondaryContainer = LightDefaultColor.secondaryContainer,
			onSecondaryContainer = LightDefaultColor.onSecondaryContainer,
			tertiary = LightDefaultColor.tertiary,
			onTertiary = LightDefaultColor.onTertiary,
			tertiaryContainer = LightDefaultColor.tertiaryContainer,
			onTertiaryContainer = LightDefaultColor.onTertiaryContainer,
			background = LightDefaultColor.background,
			onBackground = LightDefaultColor.onBackground,
			surface = LightDefaultColor.surface,
			onSurface = LightDefaultColor.onSurface,
			surfaceVariant = LightDefaultColor.surfaceVariant,
			onSurfaceVariant = LightDefaultColor.onSurfaceVariant,
			surfaceTint = LightDefaultColor.surfaceTint,
			inverseSurface = LightDefaultColor.inverseSurface,
			inverseOnSurface = LightDefaultColor.inverseOnSurface,
			error = LightDefaultColor.error,
			onError = LightDefaultColor.onError,
			errorContainer = LightDefaultColor.errorContainer,
			onErrorContainer = LightDefaultColor.onErrorContainer,
			outline = LightDefaultColor.outline,
			outlineVariant = LightDefaultColor.outlineVariant,
			scrim = LightDefaultColor.scrim,
		)
	}
	
	override val darkColorScheme: ColorScheme by lazy {
		darkColorScheme(
			primary = DarkDefaultColor.primary,
			onPrimary = DarkDefaultColor.onPrimary,
			primaryContainer = DarkDefaultColor.primaryContainer,
			onPrimaryContainer = DarkDefaultColor.onPrimaryContainer,
			inversePrimary = DarkDefaultColor.inversePrimary,
			secondary = DarkDefaultColor.secondary,
			onSecondary = DarkDefaultColor.onSecondary,
			secondaryContainer = DarkDefaultColor.secondaryContainer,
			onSecondaryContainer = DarkDefaultColor.onSecondaryContainer,
			tertiary = DarkDefaultColor.tertiary,
			onTertiary = DarkDefaultColor.onTertiary,
			tertiaryContainer = DarkDefaultColor.tertiaryContainer,
			onTertiaryContainer = DarkDefaultColor.onTertiaryContainer,
			background = DarkDefaultColor.background,
			onBackground = DarkDefaultColor.onBackground,
			surface = DarkDefaultColor.surface,
			onSurface = DarkDefaultColor.onSurface,
			surfaceVariant = DarkDefaultColor.surfaceVariant,
			onSurfaceVariant = DarkDefaultColor.onSurfaceVariant,
			surfaceTint = DarkDefaultColor.surfaceTint,
			inverseSurface = DarkDefaultColor.inverseSurface,
			inverseOnSurface = DarkDefaultColor.inverseOnSurface,
			error = DarkDefaultColor.error,
			onError = DarkDefaultColor.onError,
			errorContainer = DarkDefaultColor.errorContainer,
			onErrorContainer = DarkDefaultColor.onErrorContainer,
			outline = DarkDefaultColor.outline,
			outlineVariant = DarkDefaultColor.outlineVariant,
			scrim = DarkDefaultColor.scrim,
		)
	}
}