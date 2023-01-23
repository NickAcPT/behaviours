package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.model.RecordedReplayEntity
import io.github.nickacpt.behaviours.replay.model.standard.location.RecordableLocation

/**
 * Manages the entities during a replay session.
 * It provides methods to spawn, update, and remove entities.
 *
 * @param Entity The type of entities being managed.
 */
interface EntityManager<Entity : ReplayEntity> {

    /**
     * Spawns an entity at a given location
     *
     * @param entity The entity to spawn.
     * @param location The location to spawn the entity at.
     * @return The spawned entity
     */
    fun spawnEntity(entity: RecordedReplayEntity, location: RecordableLocation): Entity

    /**
     * Update the position of an entity
     *
     * @param entity The entity to update
     * @param location The new location to move the entity to
     */
    fun updateEntityPosition(entity: Entity, location: RecordableLocation)

    /**
     * Removes an entity from the world
     *
     * @param entity The entity to remove
     */
    fun removeEntity(entity: Entity)

}
