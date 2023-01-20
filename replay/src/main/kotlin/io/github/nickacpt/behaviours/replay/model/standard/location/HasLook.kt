package io.github.nickacpt.behaviours.replay.model.standard.location

/**
 * The [HasLook] interface represents an object that has a yaw and pitch value, typically used for
 * determining the rotation of an object in 3D space.
 */
interface HasLook {
    val yaw: Float
    val pitch: Float
}