package io.github.nickacpt.behaviours.replay.playback.session

import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.*
import io.github.nickacpt.behaviours.replay.model.Replay
import io.github.nickacpt.behaviours.replay.playback.Replayer
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.audience.ForwardingAudience

/**
 * Represents a playback session of a [Replay].
 *
 * @param system The replay system on which the replay is being played.
 * @param replay The replay being played.
 * @param viewers The viewers of the replay.
 * @param settings The settings of the replay session.
 * @param replayer The player instance that is playing the replay.
 *
 * @param ItemStack The ItemStack type of the platform.
 * @param Viewer The viewer type of the platform.
 * @param Platform The platform type.
 * @param System The ReplaySystem type.
 */
class ReplaySession<
        Viewer : ReplayViewer,
        Entity : RecordableReplayEntity,
        Platform : ReplayPlatform<Viewer, Entity>,
        System : ReplaySystem<Viewer, Entity, Platform>
        >(
    private val system: System,
    val replay: Replay,
    private val viewers: List<Viewer>,
    val replayer: Replayer<Viewer, Entity, Platform, System>,
    private val settings: ReplaySessionSettings = ReplaySessionSettings()
) : ForwardingAudience {
    /**
     * The current tick of the replay playback.
     */
    var currentTick = 0L
        private set

    var state = ReplaySessionState.LOADING
        private set

    override fun audiences(): Iterable<Audience> {
        return viewers
    }
}