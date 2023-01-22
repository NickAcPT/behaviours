package io.github.nickacpt.behaviours.replay.model

import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntityData
import io.github.nickacpt.behaviours.replay.model.standard.location.RecordableLocation

/**
 * Represents a recorded entity.
 *
 * @param id The entity id.
 * @param firstPosition The first position of the entity.
 * @param data The entity data.
 */
data class RecordedReplayEntity(
    val id: Int,
    val firstPosition: RecordableLocation,
    val data: ReplayEntityData
)