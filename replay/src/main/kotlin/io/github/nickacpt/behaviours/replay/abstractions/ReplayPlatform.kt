package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.Replayer

/**
 * Represents a platform that can be used to play and record a [Replay].
 */
interface ReplayPlatform<
        Viewer : ReplayViewer,
        World : ReplayWorld,
        Entity : ReplayEntity> {
    /**
     * Create a replayer for this platform.
     *
     * @param replaySystem The replay system in use.
     * @param replay The replay to play.
     */
    fun <Platform : ReplayPlatform<Viewer, World, Entity>,
            System : ReplaySystem<Viewer, World, Entity, Platform>> createReplayer(
        replaySystem: System,
        replay: Replay
    ): Replayer<Viewer, World, Entity, Platform, System>
}
