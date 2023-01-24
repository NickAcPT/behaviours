package io.github.nickacpt.behaviours.replay.playback.recordables.standard

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.standard.TickRecordable
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession

class TickRecordablePlayer<World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<World, Viewer, Entity>,
        System : ReplaySystem<World, Viewer, Entity, Platform>,
        Session: ReplaySession<World, Viewer, Entity, Platform, System>> : StandardRecordablePlayer<
        World, Viewer, Entity, Platform, System, Session, TickRecordable> {

    override fun Session.play(
        tick: ULong,
        recordable: TickRecordable
    ) {
        recordable.entityPositions.forEach { (id, pos) ->
            entityManager.entityMap[id]?.let { entityManager.updateEntityPosition(it, pos) }
        }
    }
}