package io.github.nickacpt.behaviours.canvas.abstractions

import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasCorner
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasPoint
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasRectangle

/**
 * Represents an abstract element that can be drawn on a canvas.
 */
interface CanvasElementAbstraction<ElementType> {

	/**
	 * The rectangle that defines the bounds of this element.
	 */
	val ElementType.rectangle: CanvasRectangle

	/**
	 * Scales this element to the given scale.
	 */
	var ElementType.elementScale: Float

	val ElementType.resizeHandleCorner: CanvasCorner
		get() = CanvasCorner.BOTTOM_RIGHT

	/**
	 * Moves this element to the given point.
	 *
	 * Inside this method, you can also do extra calculations to determine the anchor region of this element.
	 * @param point The point to move this element to.
	 */
	fun ElementType.moveTo(point: CanvasPoint)
}
