package io.github.nickacpt.behaviours.replay.model.standard.entity

import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.model.Recordable

/**
 * The [HasEntity] interface represents an object that has a reference to a [ReplayEntity].
 * It is used to associate additional information with the entity of a [Recordable].
 */
interface HasEntity {
    /**
     * The [ReplayEntity] this object is associated with.
     */
    val entity: ReplayEntity
}
