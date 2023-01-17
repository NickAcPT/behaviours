package io.github.nickacpt.behaviours.replay.playback.session

import io.github.nickacpt.behaviours.replay.Replay
import io.github.nickacpt.behaviours.replay.ReplaySystem
import io.github.nickacpt.behaviours.replay.abstractions.ReplayPlatform
import io.github.nickacpt.behaviours.replay.abstractions.ReplayViewer

/**
 * Represents a playback session of a [Replay].
 */
class ReplaySession<
        NativeItemStack,
        NativeViewer,
        Platform : ReplayPlatform<NativeItemStack, NativeViewer>,
        System : ReplaySystem<NativeItemStack, NativeViewer, Platform>
        >(
    val system: System,
    val replay: Replay,
    val viewers: List<ReplayViewer>,
    val settings: ReplaySessionSettings = ReplaySessionSettings()
) {
    /**
     * The current tick of the replay playback.
     */
    var currentTick = 0L
        private set

    var state = ReplaySessionState.LOADING
        private set
}