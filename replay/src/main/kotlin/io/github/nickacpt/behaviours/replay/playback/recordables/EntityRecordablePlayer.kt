package io.github.nickacpt.behaviours.replay.playback.recordables

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.Recordable
import io.github.nickacpt.behaviours.replay.model.standard.entity.HasEntity
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession

interface EntityRecordablePlayer<
        World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<World, Viewer, Entity>,
        System : ReplaySystem<World, Viewer, Entity, Platform>,
        Session : ReplaySession<World, Viewer, Entity, Platform, System>,
        RecordableType> : RecordablePlayer<World, Viewer, Entity, Platform, System, Session, RecordableType>
        where RecordableType : Recordable, RecordableType : HasEntity {

    fun Session.play(
        tick: ULong,
        recordable: RecordableType,
        entity: Entity
    )

    override fun Session.play(
        tick: ULong,
        recordable: RecordableType
    ) {
        val entity = recordable.entity?.id?.let { entityManager.entityMap[it] } ?: return
        play(tick, recordable, entity)
    }
}