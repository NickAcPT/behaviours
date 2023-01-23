package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.model.standard.location.RecordableLocation

/**
 * The [ReplayEntity] interface represents an entity that can be recorded.
 * It provides a unique identifier, and an object to store the current state of the entity.
 *
 * It includes a unique index-based ID, and a property to store the current state of the entity,
 * [ReplayEntityData].
 */
interface ReplayEntity {
    /**
     * The unique index-based id of this entity.
     * This is used to uniquely identify the entity and distinguish it from other entities.
     */
    val id: Int

    /**
     * The current location of the entity.
     */
    val location: RecordableLocation

    /**
     * The current entity data.
     * This stores the current state of the entity and can include information
     * such as player attributes like their game profile, horse types, armor stand information, etc.
     */
    val data: ReplayEntityData
}

