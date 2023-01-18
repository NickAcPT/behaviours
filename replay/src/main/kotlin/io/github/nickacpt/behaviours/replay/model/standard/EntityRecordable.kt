package io.github.nickacpt.behaviours.replay.model.standard

import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.model.Recordable

/**
 * Represents a recordable that is related to a [ReplayEntity].
 *
 * @param entity The entity this recordable is related to.
 */
abstract class EntityRecordable(val entity: ReplayEntity) : Recordable() {

}