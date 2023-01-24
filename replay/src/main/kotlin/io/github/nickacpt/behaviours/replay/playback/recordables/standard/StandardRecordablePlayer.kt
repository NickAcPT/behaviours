package io.github.nickacpt.behaviours.replay.playback.recordables.standard

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.standard.StandardRecordable
import io.github.nickacpt.behaviours.replay.playback.recordables.RecordablePlayer
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession

interface StandardRecordablePlayer<World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<World, Viewer, Entity>,
        System : ReplaySystem<World, Viewer, Entity, Platform>,
        Session: ReplaySession<World, Viewer, Entity, Platform, System>,
        RecordableType : StandardRecordable> :
    RecordablePlayer<World, Viewer, Entity, Platform, System, Session, RecordableType>