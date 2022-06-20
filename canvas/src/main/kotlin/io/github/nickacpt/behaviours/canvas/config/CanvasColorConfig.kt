package io.github.nickacpt.behaviours.canvas.config

/**
 * The configuration for the colour of the elements in the canvas.
 *
 *  @param elementBackground The background colour of the elements.
 *  @param elementBorder The border colour of the elements.
 *  @param selectionBackground The background colour of the selection box.
 *  @param selectionBorder The border colour of the selection box.
 */
data class CanvasColorConfig<ColorType>(
	var elementBackground: CanvasColorStyle<ColorType>? = null,
	var elementBorder: CanvasColorStyle<ColorType>? = null,

	var selectionBackground: ColorType? = null,
	var selectionBorder: ColorType? = null,

	var resizeHandleBackground: ColorType? = null,
	var resizeHandleBorder: ColorType? = null
)
