package io.github.nickacpt.behaviours.replay.model.standard.entity.state

import io.github.nickacpt.behaviours.replay.model.standard.location.RecordableLocation

/**
 * The [EntityStateType] class represents the different types of states that an entity can be in.
 * These states include when an entity is spawned, despawned, or when it swings its arm.
 */
sealed class EntityStateType {
    /**
     * Represents the state when an entity is spawned in the world.
     * @param location The location where the entity was spawned.
     */
    data class Spawn(val location: RecordableLocation) : EntityStateType()
    /**
     * Represents the state when an entity is removed from the world.
     */
    object Despawn : EntityStateType()
}