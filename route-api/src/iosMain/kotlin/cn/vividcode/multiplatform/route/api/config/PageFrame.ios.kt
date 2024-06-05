package cn.vividcode.multiplatform.route.api.config

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import cn.vividcode.multiplatform.route.api.route.PageScope
import cn.vividcode.multiplatform.route.api.theme.VividCodeTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

/**
 * PageFrame on ios.
 */
@Composable
internal actual fun PageScope.PageFrame(
	content: @Composable PageScope.() -> Unit
) {
	VividCodeTheme {
		var visible by remember { mutableStateOf(false) }
		var cancel by remember { mutableStateOf(false) }
		var dpOffset by remember { mutableStateOf(DpOffset.Zero) }
		var inverseDp by remember { mutableStateOf(0.dp) }
		var position by remember { mutableStateOf(Position.Left) }
		var screenSize by remember { mutableStateOf(DpSize.Zero) }
		var inBackArea by remember { mutableStateOf(false) }
		var startInstant by remember { mutableStateOf(nowInstant()) }
		val density = LocalDensity.current
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.background)
				.onSizeChanged {
					screenSize = it.toDpSize(density)
				}.pointerInput(IOSBackPressedPointerInputKey) {
					detectDragGestures(
						onDragStart = {
							position = when {
								it.x.toDp() <= BackIconSize -> Position.Left
								it.x.toDp() >= screenSize.width - BackIconSize -> Position.Right
								else -> return@detectDragGestures
							}
							inBackArea = true
							cancel = false
							inverseDp = 0.dp
							startInstant = nowInstant()
							dpOffset = DpOffset.Zero.copy(
								y = when {
									it.y.toDp() < VerticalBoundaryLength + BackIconSize * 2 -> VerticalBoundaryLength
									it.y.toDp() > screenSize.height - VerticalBoundaryLength + BackIconSize -> screenSize.height - VerticalBoundaryLength - BackIconSize
									else -> it.y.toDp() - BackIconSize * 2
								}
							)
						},
						onDragEnd = {
							if (visible && !cancel && abs(dpOffset.x.value) - TriggerLength.value >= BackIconSize.value) {
								back()
							} else {
								cancel = true
							}
							inBackArea = false
							visible = false
						},
						onDrag = { _, dragAmount ->
							if (inBackArea) {
								dpOffset += dragAmount.toDpOffset(
									density = density,
									y = if (dpOffset.y in VerticalBoundaryLength .. screenSize.height - VerticalBoundaryLength - BackIconSize) {
										dragAmount.y / 8
									} else {
										dragAmount.y / 12
									}
								)
								if (!visible && abs(dpOffset.x.value) > TriggerLength.value && nowInstant() - startInstant <= TriggerTime) {
									visible = true
								}
								if (visible) {
									if ((position == Position.Left && dragAmount.x < 0) || (position == Position.Right && dragAmount.x > 0)) {
										inverseDp += abs(dragAmount.x).toDp()
									} else if (((position == Position.Left && dragAmount.x > 0) || (position == Position.Right && dragAmount.x < 0))) {
										inverseDp = 0.dp
										cancel = false
									}
									if (inverseDp > InverseCancelLength) {
										dpOffset = dpOffset.copy(
											x = when (position) {
												Position.Left -> TriggerLength
												Position.Right -> -TriggerLength
											}
										)
										cancel = true
									}
								}
							}
						}
					)
				}
		) {
			content()
			BackIcon(
				visible = visible,
				cancel = cancel,
				dpOffset = dpOffset,
				position = position,
				screenSize = screenSize
			)
		}
	}
}

@Composable
private fun BackIcon(
	visible: Boolean,
	cancel: Boolean,
	dpOffset: DpOffset,
	position: Position,
	screenSize: DpSize
) {
	val dpOffsetX = when (position) {
		Position.Left -> dpOffset.x - TriggerLength
		Position.Right -> dpOffset.x + TriggerLength
	}
	val iconDpOffsetX = when (position) {
		Position.Left -> when {
			dpOffsetX <= LeftDpOffset1 -> LeftResult1
			dpOffsetX <= LeftDpOffset2 -> LeftResult2 + dpOffsetX / TriggerBackRatio
			else -> LeftResult3 + dpOffsetX / LastRatio
		}
		
		Position.Right -> when {
			dpOffsetX >= RightDpOffset1 -> RightResult1 + screenSize.width + dpOffsetX
			dpOffsetX >= RightDpOffset2 -> RightResult2 + screenSize.width + dpOffsetX / TriggerBackRatio
			else -> RightResult3 + screenSize.width + dpOffsetX / LastRatio + if (dpOffsetX <= -BoundaryIconLengthenLength) IconWidth1 + dpOffsetX / LastRatio else 0.dp
		}
	}
	val realIconDpOffsetX by animateDpAsState(
		targetValue = if (cancel) {
			when (position) {
				Position.Left -> HorizontalBoundaryLength
				Position.Right -> screenSize.width - HorizontalBoundaryLength
			}
		} else iconDpOffsetX,
		animationSpec = if (cancel) tween(durationMillis = 260) else snap()
	)
	AnimatedVisibility(
		visible = visible && !cancel,
		modifier = Modifier
			.offset(x = realIconDpOffsetX, y = dpOffset.y),
		enter = EnterTransition,
		exit = if (cancel) ExitCancelTransition else ExitBackTransition
	) {
		val width = when (position) {
			Position.Left -> when {
				dpOffsetX <= 0.dp -> 0.dp
				dpOffsetX <= BackIconSize -> dpOffsetX
				dpOffsetX <= BoundaryIconLengthenLength -> BackIconSize
				else -> BackIconSize - IconWidth1 + dpOffsetX / LastRatio
			}
			
			Position.Right -> when {
				dpOffsetX >= 0.dp -> 0.dp
				dpOffsetX >= -BackIconSize -> -dpOffsetX
				dpOffsetX >= -BoundaryIconLengthenLength -> BackIconSize
				else -> BackIconSize - IconWidth1 - dpOffsetX / LastRatio
			}
		}
		val realWidth by animateDpAsState(
			targetValue = if (cancel) 0.dp else width,
			animationSpec = if (cancel) tween(durationMillis = 260) else snap()
		)
		Box(
			modifier = Modifier
				.width(realWidth)
				.height(BackIconSize)
				.clip(RoundedCornerShape(if (realWidth < BackIconSize) realWidth / 2F else BackIconCorner))
				.background(MaterialTheme.colorScheme.secondaryContainer)
		) {
			Icon(
				imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
				contentDescription = null,
				modifier = Modifier
					.size(BackIconSize - 8.dp)
					.align(Alignment.Center),
				tint = MaterialTheme.colorScheme.onSurface
			)
		}
	}
}

private val BackIconSize = 52.dp

private val BackIconCorner = BackIconSize / 2F

private val TriggerLength = 30.dp

private val HorizontalBoundaryLength = 5.dp

private val VerticalBoundaryLength = 150.dp

private val TriggerTime = 300.milliseconds

private val TriggerBackLength = 10.dp

private const val TriggerBackRatio = 1.5F

private const val LastRatio = 20F

private val InverseCancelLength = BackIconSize

private val BoundaryIconLengthenLength = BackIconSize * 3

private val LeftDpOffset1 = BackIconSize

private val LeftDpOffset2 = BackIconSize + TriggerBackLength * TriggerBackRatio

private val RightDpOffset1 = -BackIconSize

private val RightDpOffset2 = -BackIconSize - TriggerBackLength * TriggerBackRatio

private val LeftResult1 = HorizontalBoundaryLength

private val LeftResult2 = -LeftDpOffset1 / TriggerBackRatio + HorizontalBoundaryLength

private val LeftResult3 = TriggerBackLength - LeftDpOffset2 / LastRatio + HorizontalBoundaryLength

private val RightResult1 = -HorizontalBoundaryLength

private val RightResult2 = -BackIconSize + BackIconSize / TriggerBackRatio - HorizontalBoundaryLength

private val RightResult3 = -BackIconSize - TriggerBackLength + LeftDpOffset2 / LastRatio - HorizontalBoundaryLength

private val IconWidth1 = BoundaryIconLengthenLength / LastRatio

private enum class Position {
	Left,
	Right
}

private val IOSBackPressedPointerInputKey = Any()

private val EnterTransition = fadeIn(animationSpec = tween(260), initialAlpha = 0.8F)

private val ExitBackTransition = fadeOut(
	animationSpec = tween(
		durationMillis = 180,
		delayMillis = 80
	)
) + scaleOut(
	animationSpec = keyframes {
		durationMillis = 260
		1F at 0
		1.1F at 80
		0.55F at 180
	},
	targetScale = 0.5F
)

private val ExitCancelTransition = fadeOut(
	animationSpec = tween(
		durationMillis = 260
	),
	targetAlpha = 0.5F
)

private fun nowInstant(): Instant = Clock.System.now()

private fun IntSize.toDpSize(
	density: Density,
	width: Int = this.width,
	height: Int = this.height
): DpSize = DpSize(
	width = (width / density.density).dp,
	height = (height / density.density).dp
)

/**
 * Offset -> DpOffset
 */
private fun Offset.toDpOffset(
	density: Density,
	x: Float = this.x,
	y: Float = this.y
): DpOffset = DpOffset(
	x = (x / density.density).dp,
	y = (y / density.density).dp
)