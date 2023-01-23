package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.model.RecordedReplayEntity
import io.github.nickacpt.behaviours.replay.model.standard.location.RecordableLocation

interface EntityManager<Entity : ReplayEntity> {

    fun spawnEntity(entity: RecordedReplayEntity, location: RecordableLocation): Entity

    fun updateEntityPosition(entity: Entity, location: RecordableLocation)

    fun removeEntity(entity: Entity)

}
