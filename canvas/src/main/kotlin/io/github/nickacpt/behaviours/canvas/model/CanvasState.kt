package io.github.nickacpt.behaviours.canvas.model

import io.github.nickacpt.behaviours.canvas.model.geometry.CanvasPoint

internal data class CanvasState<ElementType>(
	val actionChangedHandler: (CanvasAction) -> Unit = {},
) {
	var currentAction: CanvasAction = CanvasAction.NONE
		set(value) {
			field = value
			actionChangedHandler(value)
		}

	var mouseDown: Boolean = false
	var mouseDownPosition: CanvasPoint? = null

	val lastRenderMousePosition: CanvasPoint = CanvasPoint(0f, 0f)
	val selectedElements: MutableSet<ElementType> = mutableSetOf()

	var lastClickedElement: ElementType? = null

}
