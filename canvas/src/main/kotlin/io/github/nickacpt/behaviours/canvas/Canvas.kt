package io.github.nickacpt.behaviours.canvas

import io.github.nickacpt.behaviours.canvas.abstractions.CanvasAbstraction
import io.github.nickacpt.behaviours.canvas.config.CanvasConfig
import io.github.nickacpt.behaviours.canvas.config.CanvasResizeHandleVisibility
import io.github.nickacpt.behaviours.canvas.model.CanvasAction
import io.github.nickacpt.behaviours.canvas.model.CanvasState
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasPoint
import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasRectangle

class Canvas<ElementType, ColorType>(
    internal val abstraction: CanvasAbstraction<ElementType, ColorType>,
    internal val config: CanvasConfig<ColorType>
) {
    internal val state = CanvasState<ElementType> {
        snapper.notifyStateChange(it)
        resizer.notifyStateChanged()
    }
    internal val resizer = CanvasResizer(this)
    private val renderer = CanvasRenderer(this)
    private val snapper = CanvasSnapper(this)

    internal val safeZoneRectangle
        get() = abstraction.canvasRectangle.expand(-config.safeZoneSize)

    fun onRender(mousePosition: CanvasPoint) {
        renderer.renderBackground(mousePosition)
        handleAction(mousePosition)

        state.lastRenderMousePosition.apply {
            x = mousePosition.x
            y = mousePosition.y
        }
    }

    private fun handleAction(mousePosition: CanvasPoint) {
        when (state.currentAction) {
            CanvasAction.ELEMENT_MOVE -> {
                moveElements(mousePosition)
            }

            CanvasAction.ELEMENT_SELECT -> {
                selectElements(mousePosition)
            }

            CanvasAction.ELEMENT_RESIZE -> {
                resizeElements(mousePosition)
            }

            CanvasAction.NONE -> {
                // do nothing
            }

            else -> {
                throw IllegalStateException("Unknown action: ${state.currentAction}")
            }
        }
    }

    private fun selectElements(mousePosition: CanvasPoint) {
        val mouseDownPos = state.mouseDownPosition ?: return
        val topLeftX = minOf(mouseDownPos.x, mousePosition.x)
        val topLeftY = minOf(mouseDownPos.y, mousePosition.y)

        val bottomRightX = maxOf(mouseDownPos.x, mousePosition.x)
        val bottomRightY = maxOf(mouseDownPos.y, mousePosition.y)

        val selectionRectangle =
            CanvasRectangle(CanvasPoint(topLeftX, topLeftY), CanvasPoint(bottomRightX, bottomRightY))

        config.colors.selectionBackground?.let { abstraction.drawRectangle(selectionRectangle, it, false, 0f) }
        config.colors.selectionBorder?.let { abstraction.drawRectangle(selectionRectangle, it, true, 1f) }

        for (element in abstraction.elements) {
            val corners = with(abstraction) { element.rectangle }.corners()

            if (corners.any { selectionRectangle.contains(it) }) {
                state.selectedElements.add(element)
            } else {
                // TODO: only restart if not pressing control
                state.selectedElements.remove(element)
            }
        }
    }

    private fun moveElements(mousePosition: CanvasPoint) {
        if (state.selectedElements.isEmpty()) return
        val delta = mousePosition - state.lastRenderMousePosition

        state.selectedElements.forEach {
            with(abstraction) {
                val elementRect = it.rectangle
                val canvasRect = safeZoneRectangle
                val newRectangle = elementRect.copy(
                    topLeft = elementRect.topLeft + delta,
                    bottomRight = elementRect.bottomRight + delta
                )
                val point = newRectangle.topLeft

                if (state.selectedElements.size == 1) snapper.snap(mousePosition, newRectangle, point)

                point.apply {
                    x = x.coerceIn(canvasRect.left, canvasRect.right - elementRect.width)
                    y = y.coerceIn(canvasRect.top, canvasRect.bottom - elementRect.height)
                }

                it.moveTo(point)
            }
        }

        snapper.notifyStateChange(CanvasAction.ELEMENT_MOVE)
    }

    private fun resizeElements(mousePosition: CanvasPoint) {
        if (state.selectedElements.size != 1) return
        val element = state.selectedElements.first()

        resizer.resize(element, mousePosition)
    }

    fun onMouseDown(mousePosition: CanvasPoint) {
        state.mouseDown = true
        state.mouseDownPosition = mousePosition.copy()

        snapper.notifyStateChange(CanvasAction.NONE)
        computeCurrentAction(mousePosition)

        for (element in abstraction.elements) {
            val rect = with(abstraction) { element.rectangle }
            val resizeRect = resizer.takeIf { config.resizeHandleVisibility != CanvasResizeHandleVisibility.NEVER }
                ?.let { with(it) { element.resizeHandleRectangle } }
            val isResizing = resizeRect?.contains(mousePosition) == true

            if (rect.contains(mousePosition) || isResizing) {

                val holdingMultiSelectKey = false /* TODO: detect if holding control */
                if (isResizing || (!holdingMultiSelectKey &&
                            state.currentAction == CanvasAction.ELEMENT_MOVE &&
                            state.selectedElements.isNotEmpty() &&
                            !state.selectedElements.contains(element)
                            )
                ) {
                    state.selectedElements.clear()
                }

                state.selectedElements.add(element)

                return
            }
        }

        state.selectedElements.clear()
    }

    fun onMouseUp(mousePosition: CanvasPoint) {
        state.mouseDown = false
        state.mouseDownPosition = null

        if (state.currentAction == CanvasAction.ELEMENT_MOVE && state.selectedElements.size == 1) {
            state.selectedElements.clear()
        }

        computeCurrentAction(mousePosition)
    }

    private fun computeCurrentAction(mousePosition: CanvasPoint) {
        state.currentAction = CanvasAction.NONE

        val hoveredElement =
            abstraction.elements.firstOrNull { with(abstraction) { it.rectangle }.contains(mousePosition) }

        val resizedElement =
            abstraction.elements.takeIf { config.resizeHandleVisibility != CanvasResizeHandleVisibility.NEVER }
                ?.firstOrNull { with(resizer) { it.resizeHandleRectangle }.contains(mousePosition) }

        val overAnyElement = hoveredElement != null
        if (!state.mouseDown) return

        state.currentAction =
            if (resizedElement != null && state.selectedElements.size <= 1) {
                CanvasAction.ELEMENT_RESIZE
            } else if (overAnyElement) {
                CanvasAction.ELEMENT_MOVE
            } else {
                CanvasAction.ELEMENT_SELECT
            }
    }
}
