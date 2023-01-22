package io.github.nickacpt.behaviours.replay.model.standard.entity

import io.github.nickacpt.behaviours.replay.abstractions.RecordableReplayEntity
import io.github.nickacpt.behaviours.replay.model.Recordable
import io.github.nickacpt.behaviours.replay.model.RecordedReplayEntity

/**
 * The [HasEntity] interface represents an object that has a reference to a [RecordableReplayEntity].
 * It is used to associate additional information with the entity of a [Recordable].
 */
interface HasEntity {
    /**
     * The [RecordableReplayEntity] this object is associated with.
     */
    val entity: RecordedReplayEntity
}
