package io.github.nickacpt.behaviours.replay.model.standard.location

/**
 * The [RecordableLocationWithoutLook] data class represents a location that has only the position
 * (x,y,z) of an object in 3D space.
 */
data class RecordableLocationWithoutLook(
    override val x: Double,
    override val y: Double,
    override val z: Double
) : RecordableLocation