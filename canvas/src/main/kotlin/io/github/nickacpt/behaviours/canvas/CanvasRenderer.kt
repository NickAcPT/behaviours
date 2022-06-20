package io.github.nickacpt.behaviours.canvas

import io.github.nickacpt.behaviours.canvas.config.CanvasColorStyle
import io.github.nickacpt.behaviours.canvas.config.CanvasResizeHandleVisibility
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasPoint
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasRectangle

class CanvasRenderer<ElementType, ColorType>(val canvas: Canvas<ElementType, ColorType>) {

    private fun getBackgroundColorToUse(element: ElementType, rect: CanvasRectangle, mousePosition: CanvasPoint, bgColor: CanvasColorStyle<ColorType>): ColorType {
        val containsMouse = rect.contains(mousePosition)
        return if (canvas.state.selectedElements.contains(element)) {
            bgColor.selected
        } else if (containsMouse && canvas.state.mouseDown) {
            bgColor.mouseDown
        } else if (containsMouse) {
            bgColor.hover
        } else {
            bgColor.normal
        } ?: bgColor.normal
    }

    private fun renderSafeZone() = with(canvas) {
        val color = config.colors.selectionBackground ?: return
        val safeZone = abstraction.canvasRectangle.expand(-config.safeZoneSize)

        abstraction.drawRectangle(safeZone, color, true, config.snapLineWidth)
    }

    private fun Canvas<ElementType, ColorType>.renderResizeHandleDecorator(resizeHandleRectangle: CanvasRectangle) {

        config.colors.resizeHandleBackground?.let { handleBackground ->
            abstraction.drawRectangle(resizeHandleRectangle, handleBackground, false, 0f)
        }

        config.colors.resizeHandleBorder?.let { handleBorder ->
            abstraction.drawRectangle(resizeHandleRectangle, handleBorder, true, config.resizeHandleBorderWidth)
        }
    }

    private fun renderElementDecorators(canvasElements: Collection<ElementType>, mousePosition: CanvasPoint) =
        with(canvas) {
            for (element in canvasElements) {
                val elementRectangle = with(abstraction) { element.rectangle }
                val backgroundColor =
                    config.colors.elementBackground?.let { getBackgroundColorToUse(element, elementRectangle, mousePosition, it) }

                if (backgroundColor != null) {
                    abstraction.drawRectangle(elementRectangle, backgroundColor, false, 0f)
                }

                val borderColor =
                    config.colors.elementBorder?.let { getBackgroundColorToUse(element, elementRectangle, mousePosition, it) }
                if (borderColor != null) {
                    abstraction.drawRectangle(elementRectangle, borderColor, true, config.borderWidth)
                }

                val elementResizeRenderRect = with(resizer) { element.resizeHandleRenderRectangle }
                val elementResizeRect = with(resizer) { element.resizeHandleRectangle }

				if (shouldRenderResizeHandle(mousePosition, elementRectangle, elementResizeRect, elementResizeRenderRect)) {
                    renderResizeHandleDecorator(elementResizeRect)
                }

            }
        }

    private fun shouldRenderResizeHandle(mousePosition: CanvasPoint, elementRect: CanvasRectangle, resizeHandleRectangle: CanvasRectangle, resizeHandleRenderRectangle: CanvasRectangle): Boolean {
        if (canvas.state.selectedElements.size > 1) {
            return false
        }

        return with(canvas) {
            when (config.resizeHandleVisibility) {
                CanvasResizeHandleVisibility.NEVER -> false
                CanvasResizeHandleVisibility.ON_HOVER -> elementRect.contains(mousePosition) || resizeHandleRectangle.contains(mousePosition)
                CanvasResizeHandleVisibility.ON_CORNER_HOVER -> resizeHandleRenderRectangle.contains(mousePosition)
                CanvasResizeHandleVisibility.ALWAYS -> true
            }
        }
    }

    fun renderBackground(mousePosition: CanvasPoint) = with(canvas) {
        renderSafeZone()
        renderElementDecorators(abstraction.elements, mousePosition)
    }

}
