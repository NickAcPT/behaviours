package io.github.nickacpt.behaviours.replay.playback.recordables.standard

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld

object StandardRecordPlayerProvider {
    internal fun <
            World : ReplayWorld,
            Viewer : ReplayViewer<World>,
            Entity : ReplayEntity,
            Platform : ReplayPlatform<World, Viewer, Entity>>
            ReplaySystem<World, Viewer, Entity, Platform>.registerStandardRecordablePlayers() {
        registerRecordablePlayer(TickRecordablePlayer())
    }

}