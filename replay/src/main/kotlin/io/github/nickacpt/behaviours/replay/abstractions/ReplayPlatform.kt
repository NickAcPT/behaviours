package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.Replayer

/**
 * Represents a platform that can be used to play and record a [Replay].
 */
interface ReplayPlatform<
        Viewer : ReplayViewer,
        Entity : RecordableReplayEntity> {
    /**
     * Create a replayer for this platform.
     *
     * @param replaySystem The replay system in use.
     * @param replay The replay to play.
     */
    fun <Platform : ReplayPlatform<Viewer, Entity>,
            System : ReplaySystem<Viewer, Entity, Platform>> createReplayer(
        replaySystem: System,
        replay: Replay
    ): Replayer<Viewer, Entity, Platform, System>
}
