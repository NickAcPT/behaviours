package io.github.nickacpt.behaviours.replay.model.standard.location

data class RecordableLocationWithoutLook(
    override val x: Double,
    override val y: Double,
    override val z: Double
) : RecordableLocation