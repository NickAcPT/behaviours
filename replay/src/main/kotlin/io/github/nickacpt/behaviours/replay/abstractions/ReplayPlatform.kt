package io.github.nickacpt.behaviours.replay.abstractions

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.Replayer
import io.github.nickacpt.behaviours.replay.record.RecordingConfiguration
import io.github.nickacpt.behaviours.replay.record.ReplayRecorder

/**
 * Represents a platform that can be used to play and record a [Replay].
 */
interface ReplayPlatform<
        World : ReplayWorld,
        Viewer : ReplayViewer<World>,
        Entity : ReplayEntity> {
    /**
     * Create a replayer for this platform.
     *
     * @param replaySystem The replay system in use.
     * @param replay The replay to play.
     */
    fun <Platform : ReplayPlatform<World, Viewer, Entity>,
            System : ReplaySystem<World, Viewer, Entity, Platform>> createReplayer(
        replaySystem: System,
        replay: Replay
    ): Replayer<World, Viewer, Entity, Platform, System>

    /**
     * Schedule a repeating task.
     *
     * @param delay The delay between each execution of the task (in ticks).
     * @param task The task to execute.
     */
    fun registerRepeatingTask(
        delay: Long,
        task: () -> Unit
    )

    fun <Platform : ReplayPlatform<World, Viewer, Entity>,
            System : ReplaySystem<World, Viewer, Entity, Platform>> createReplayRecorder(
        replaySystem: System,
        world: World,
        entities: List<Entity>,
        configuration: RecordingConfiguration
    ): ReplayRecorder<World, Viewer, Entity, Platform, System>
}
