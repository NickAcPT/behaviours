package io.github.nickacpt.behaviours.canvas.config

data class CanvasColorStyle<ColorType> @JvmOverloads constructor(
	var normal: ColorType,
	var hover: ColorType,
	var selected: ColorType = hover,
	var mouseDown: ColorType = hover,
)
