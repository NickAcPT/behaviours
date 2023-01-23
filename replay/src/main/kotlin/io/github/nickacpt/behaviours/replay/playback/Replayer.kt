package io.github.nickacpt.behaviours.replay.playback

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.*
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
 * @param World The world type of the platform.
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
     * @return The prepared world for the replay session
     */
    fun prepareReplaySession(
        replay: Replay,
        replaySession: ReplaySession<Viewer, World, Entity, Platform, System>,
        replayViewers: List<Viewer>
    ): World

    /**
     * Creates an Entity manager for the given replay system and session.
     *
     * @param replaySystem The replay system being used.
     * @param replaySession The replay session to create the Entity manager for.
     * @return an Entity manager for the given replay system and session.
     */
    fun <Platform : ReplayPlatform<Viewer, World, Entity>,
            System : ReplaySystem<Viewer, World, Entity, Platform>,
            Session : ReplaySession<Viewer, World, Entity, Platform, System>> createEntityManager(
        replaySystem: System,
        replaySession: Session
    ): EntityManager<Entity>


    /**
     * Update the state of the replay session for the given viewer.
     *
     * This state usually includes things like the current tick, the current state of the session, etc.
     *
     * @param replaySession The replay session to update the state for.
     * @param viewer The viewer for which to update the state.
     */
    fun updateReplaySessionStateForViewer(
        replaySession: ReplaySession<Viewer, World, Entity, Platform, System>,
        viewer: Viewer,
    )

    /**
     * Update the controls of the replay session for the given viewer.
     *
     * @param replaySession The replay session to update the controls for.
     * @param viewer The viewer for which to update the controls.
     */
    fun updateReplaySessionViewerControls(
        replaySession: ReplaySession<Viewer, World, Entity, Platform, System>,
        viewer: Viewer,
    )

}