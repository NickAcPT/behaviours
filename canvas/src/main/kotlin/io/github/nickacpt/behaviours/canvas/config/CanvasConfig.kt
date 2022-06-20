package io.github.nickacpt.behaviours.canvas.config

/**
 * The configuration for the canvas behaviour
 *
 * @param colors The colors to use for the canvas
 * @param borderWidth The width of the border for elements
 * @param safeZoneSize The size of the safe zone for elements
 * @param snapDistance The distance to snap to when dragging
 * @param mouseExitSnapDistance The distance to stop snapping when the mouse is too far away from a guideline
 */
data class CanvasConfig<ColorType> @JvmOverloads constructor(
	val colors: CanvasColorConfig<ColorType> = CanvasColorConfig(),
	var borderWidth: Float = 1f,
	var safeZoneSize: Float = 4f,

	var snapLineWidth: Float = 1f,
	var generateMiddleSnapLines: Boolean = true,
	var elementsHaveMiddleSnapLines: Boolean = true,

	var snapDistance: Float = 5f,
	var mouseExitSnapDistance: Float = snapDistance * 2f,

	var resizeHandleVisibility: CanvasResizeHandleVisibility = CanvasResizeHandleVisibility.ON_CORNER_HOVER,
	var resizeHandleSize: Float = 5f,
	var resizeHandleRenderSize: Float = resizeHandleSize * 3.5f,
	var resizeHandleBorderWidth: Float = 1f,
)

