package io.github.nickacpt.behaviours.canvas.model.geometry

data class CanvasPoint(var x: Float, var y: Float) {

	operator fun plus(other: CanvasPoint): CanvasPoint {
		return CanvasPoint(x + other.x, y + other.y)
	}

	operator fun minus(other: CanvasPoint): CanvasPoint {
		return CanvasPoint(x - other.x, y - other.y)
	}

	operator fun times(factor: Float): CanvasPoint {
		return CanvasPoint(x * factor, y * factor)
	}

	operator fun div(factor: Float): CanvasPoint {
		return CanvasPoint(x / factor, y / factor)
	}

}
