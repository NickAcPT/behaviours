package io.github.nickacpt.behaviours.replay.model.standard.location

/**
 * The [RecordableLocation] interface represents an object that has a set of coordinates (x,y,z)
 * typically used for determining the position of an object in 3D space.
 */
interface RecordableLocation {
    val x: Double
    val y: Double
    val z: Double
}