package io.github.nickacpt.behaviours.replay.model.standard.location

data class RecordableLocationWithLook(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val yaw: Float,
    override val pitch: Float
) : RecordableLocation, HasLook