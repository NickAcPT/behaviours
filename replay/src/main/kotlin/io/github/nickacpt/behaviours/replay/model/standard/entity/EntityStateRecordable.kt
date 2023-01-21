package io.github.nickacpt.behaviours.replay.model.standard.entity

import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.model.standard.StandardRecordable
import io.github.nickacpt.behaviours.replay.model.standard.entity.state.EntityStateType

data class EntityStateRecordable(
    val type: EntityStateType,
    override val entity: ReplayEntity
) : StandardRecordable(), HasEntity
