package io.github.nickacpt.behaviours.canvas.abstractions

import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasPoint
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasRectangle

/**
 * Represents an abstracted canvas that can be used to draw on.
 */
interface CanvasAbstraction<ElementType, ColorType> : CanvasElementAbstraction<ElementType> {

	/**
	 * The rectangle that bounds the canvas.
	 *
	 * Basically, this is the whole screen rectangle.
	 */
	val canvasRectangle: CanvasRectangle

	/**
	 * The elements in the canvas.
	 *
	 * These are the elements that are drawn on the canvas by the user.
	 */
	val elements: Collection<ElementType>

	/**
	 * Draws a line on the canvas.
	 *
	 * @param start The start point of the line.
	 * @param end The end point of the line.
	 * @param color The color of the line.
	 * @param lineWidth The thickness of the line.
	 */
	fun drawLine(start: CanvasPoint, end: CanvasPoint, color: ColorType, lineWidth: Float)

	/**
	 * Draws a rectangle on the canvas.
	 *
	 * @param rectangle The rectangle to draw.
	 * @param color The color of the rectangle.
	 * @param hollow Whether the rectangle is hollow or not (filled vs just border)
	 * @param lineWidth The thickness of the border, if hollow.
	 */
	fun drawRectangle(rectangle: CanvasRectangle, color: ColorType, hollow: Boolean, lineWidth: Float)
}
