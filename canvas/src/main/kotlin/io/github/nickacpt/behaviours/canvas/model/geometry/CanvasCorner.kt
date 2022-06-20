package io.github.nickacpt.behaviours.canvas.model.geometry

import io.github.nickacpt.behaviours.canvas.model.geometry.line.CanvasLineSide

enum class CanvasCorner(val inverted: () -> CanvasCorner, internal val side: CanvasLineSide) {
    TOP_LEFT({ BOTTOM_RIGHT }, CanvasLineSide.FIRST),
    TOP_RIGHT({ BOTTOM_LEFT }, CanvasLineSide.SECOND),
    BOTTOM_LEFT({ TOP_RIGHT }, CanvasLineSide.FIRST),
    BOTTOM_RIGHT({ TOP_LEFT }, CanvasLineSide.SECOND)
}