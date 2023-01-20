package io.github.nickacpt.behaviours.replay.model.standard.location

/**
 * The [RecordableLocationWithLook] data class represents a location that has both the position
 * (x,y,z) and look (yaw, pitch) of an object in 3D space.
 */
data class RecordableLocationWithLook(
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val yaw: Float,
    override val pitch: Float
) : RecordableLocation, HasLook