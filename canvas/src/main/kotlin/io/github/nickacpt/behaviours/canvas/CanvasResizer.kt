package io.github.nickacpt.behaviours.canvas

import io.github.nickacpt.behaviours.canvas.model.CanvasAction
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasPoint
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasRectangle
import kotlin.math.min

class CanvasResizer<ElementType, ColorType>(val canvas: Canvas<ElementType, ColorType>) {

    private var elementRectangleCache: CanvasRectangle? = null
    private var elementScaleCache: Float? = null

    internal fun notifyStateChanged() {
        elementRectangleCache = null
        elementScaleCache = null
    }

    fun resize(element: ElementType, mousePosition: CanvasPoint) {
        val elementRectangle = elementRectangleCache ?: with(canvas.abstraction) { element.rectangle }
        val elementScale = elementScaleCache ?: with(canvas.abstraction) { element.elementScale }

        if (elementRectangleCache == null) elementRectangleCache = elementRectangle
        if (elementScaleCache == null) elementScaleCache = elementScale

        val oppositeHandleCorner = with(canvas.abstraction) { element.resizeHandleCorner }.inverted()
        val oppositeHandleCornerPoint = elementRectangle.getCorner(oppositeHandleCorner)

        val xPos = listOf(oppositeHandleCornerPoint.x, mousePosition.x)
        val yPos = listOf(oppositeHandleCornerPoint.y, mousePosition.y)

        val topLeft = CanvasPoint(xPos.min(), yPos.min())
        val bottomRight = CanvasPoint(xPos.max(), yPos.max())

        val maxRectangle = CanvasRectangle(
            topLeft,
            bottomRight
        )

        val newScale = min(maxRectangle.height / elementRectangle.height, maxRectangle.width / elementRectangle.width)
        val scale = elementScale * newScale

        with(canvas.abstraction) { element.elementScale = scale }
    }

    val ElementType.resizeHandleRectangle: CanvasRectangle
        get() = computeResizeHandleRect(canvas.config.resizeHandleSize)

    val ElementType.resizeHandleRenderRectangle: CanvasRectangle
        get() = computeResizeHandleRect(canvas.config.resizeHandleRenderSize)

    private fun ElementType.computeResizeHandleRect(size: Float) =
        with(canvas.abstraction) { rectangle.getCorner(resizeHandleCorner) }.let {
            CanvasRectangle(it, it).expand(size / 2f)
        }

}
