package io.github.nickacpt.behaviours.replay.model.standard.entity

import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.model.standard.StandardRecordable
import io.github.nickacpt.behaviours.replay.model.standard.entity.state.EntityStateType

/**
 * The [EntityStateRecordable] data class represents a recordable of the state of a [ReplayEntity].
 * It includes the type of state the entity is in as well as a reference to the entity itself.
 * It implements [HasEntity] interface, which provides association of additional information with the entity.
 *
 * @property type The type of state the entity is in.
 * @property entity The [ReplayEntity] this recordable is related to.
 */
data class EntityStateRecordable(
    val type: EntityStateType,
    override val entity: ReplayEntity
) : StandardRecordable(), HasEntity
