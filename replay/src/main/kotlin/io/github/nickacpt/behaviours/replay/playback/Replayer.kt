package io.github.nickacpt.behaviours.replay.playback

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayEntity
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer
import io.github.nickacpt.behaviours.replay.abstractions.ReplayWorld
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.session.ReplaySession

/**
 * The [Replayer] interface is used to control the playback of a [Replay].
 * It provides a way to prepare a [ReplaySession] for a specific [Replay] and set of [ReplayViewer]s.
 *
 * It takes in a number of generic types that represent the specific implementation
 * details of the platform it is being used on.
 *
 * @param Viewer The viewer type of the platform.
 * @param Entity The entity type of the platform.
 * @param Platform The platform in which the replay system exists.
 * @param System The replay system being used.
 */
interface Replayer<
        Viewer : ReplayViewer,
        World : ReplayWorld,
        Entity : ReplayEntity,
        Platform : ReplayPlatform<Viewer, World, Entity>,
        System : ReplaySystem<Viewer, World, Entity, Platform>> {

    /**
     * Prepare the replay session for the given replay, replay session, and replay viewers
     *
     * @param replay The replay to prepare the session for.
     * @param replaySession The replay session to prepare.
     * @param replayViewers The list of replay viewers to prepare the session for.
     */
    fun prepareReplaySession(
        replay: Replay,
        replaySession: ReplaySession<Viewer, World, Entity, Platform, System>,
        replayViewers: List<Viewer>
    ): World

}